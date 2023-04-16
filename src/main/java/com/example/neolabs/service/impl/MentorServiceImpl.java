package com.example.neolabs.service.impl;

import com.example.neolabs.dto.MentorCardDto;
import com.example.neolabs.dto.MentorDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.create.CreateMentorRequest;
import com.example.neolabs.dto.request.update.UpdateMentorRequest;
import com.example.neolabs.entity.Mentor;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.GroupMapper;
import com.example.neolabs.mapper.MentorMapper;
import com.example.neolabs.repository.CourseRepository;
import com.example.neolabs.repository.GroupRepository;
import com.example.neolabs.repository.MentorRepository;
import com.example.neolabs.service.ImageUploadService;
import com.example.neolabs.service.MentorService;
import com.example.neolabs.util.DateUtil;
import com.example.neolabs.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

    final MentorRepository mentorRepository;
    final MentorMapper mentorMapper;
    // TODO: 14.04.2023 if possible its better to NOT use other repos in service class
    final CourseRepository courseRepository;
    final GroupRepository groupRepository;
    final CourseServiceImpl courseService;
    final GroupMapper groupMapper;
    final ImageUploadService imageUploadService;

    @Override
    public List<MentorCardDto> getAllMentorCards(Long courseId, Status status) {
        ExampleMatcher exampleMatcher = getExampleMatcherForCards();
        Mentor probe = Mentor.builder()
                .status(status)
                .course(courseId != null ? courseService.getCourseEntityById(courseId) : null)
                .build();
        return mentorMapper.entityListToCardList(mentorRepository.findAll(Example.of(probe, exampleMatcher)));
    }

    @Override
    public Long addNewMentor(CreateMentorRequest createMentorRequest) {
        if (!mentorRepository.existsByEmail(createMentorRequest.getEmail())) {
            mentorRepository.saveAndFlush(mentorMapper.createMentorDtoToMentorEntity(createMentorRequest));
        } else{
            throw new BaseException("mentor with email " + createMentorRequest.getEmail() + " already exists", HttpStatus.BAD_REQUEST);}
        Mentor mentor = mentorRepository.findByEmail(createMentorRequest.getEmail()).orElseThrow(()->
                new BaseException("Not found mentor with email "+createMentorRequest.getEmail(),HttpStatus.BAD_REQUEST));
        return mentor.getId();
    }

    @Override
    public String saveImageMentor(Long mentorId, MultipartFile multipartFile) {
        Mentor mentor = getMentorEntityById(mentorId);
        mentor.setImageUrl(imageUploadService.saveImage(multipartFile));
        mentorRepository.save(mentor);
        return "saved image for mentor " + mentorId;
    }


    @Override
    public void deleteMentorById(Long id) {
        Mentor mentor = getMentorEntityById(id);
        mentor.setStatus(Status.DELETED);
        mentor.setDeletedDate(LocalDateTime.now());
        mentorRepository.save(mentor);
    }

    @Override
    public void updateMentorById(UpdateMentorRequest updateMentorRequest, Long id) {
        Mentor mentor = getMentorEntityById(id);
        mentor.setEmail(updateMentorRequest.getEmail());
        mentor.setFirstName(updateMentorRequest.getFirstName());
        mentor.setLastName(updateMentorRequest.getLastName());
        mentor.setPhoneNumber(updateMentorRequest.getPhoneNumber());
        mentor.setPatentNumber(updateMentorRequest.getPatentNumber());
        mentor.setSalary(updateMentorRequest.getSalary());
        mentor.setCourse(courseRepository.findById(updateMentorRequest.getCourseId())
                .orElseThrow(
                        () -> new BaseException("course with id  " + updateMentorRequest.getCourseId() + " not found", HttpStatus.BAD_REQUEST)));
        mentorRepository.save(mentor);
    }

    @Override
    public ResponseDto archiveMentorById(Long mentorId, ArchiveRequest archiveRequest, Boolean isBlacklist) {
        Mentor mentor = getMentorEntityById(mentorId);
        if (mentor.getStatus() == Status.ARCHIVED && !isBlacklist) {
            throw new BaseException("Mentor is already archived", HttpStatus.CONFLICT);
        }
        if (mentor.getStatus() == Status.BLACKLIST && isBlacklist) {
            throw new BaseException("Mentor is already in blacklist", HttpStatus.CONFLICT);
        }
        mentor.setReason(archiveRequest.getReason());
        mentor.setStatus(isBlacklist ? Status.BLACKLIST : Status.ARCHIVED);
        mentor.setArchiveDate(LocalDateTime.now(DateUtil.getZoneId()));
        mentorRepository.save(mentor);
        return ResponseUtil.buildSuccessResponse("Mentor has been successfully " + (isBlacklist ? "blacklisted." : "archived."));
    }

    @Override
    public ResponseDto unarchiveMentorById(Long mentorId) {
        Mentor mentor = getMentorEntityById(mentorId);
        if (mentor.getStatus() == Status.ACTIVE) {
            throw new BaseException("Mentor is already active.", HttpStatus.CONFLICT);
        }
        mentor.setStatus(Status.ACTIVE);
        mentor.setReason(null);
        mentor.setArchiveDate(null);
        mentorRepository.save(mentor);
        return ResponseUtil.buildSuccessResponse("Mentor has been successfully unarchived.");
    }

    @Override
    public MentorDto getMentorById(Long id) {
        Mentor mentor = getMentorEntityById(id);
        MentorDto mentorDto = new MentorDto();
        mentorDto.setFirstName(mentor.getFirstName());
        mentorDto.setLastName(mentor.getLastName());
        mentorDto.setPhoneNumber(mentor.getPhoneNumber());
        mentorDto.setPatentNumber(mentor.getPatentNumber());
        mentorDto.setEmail(mentor.getEmail());
        mentorDto.setCourseName(mentor.getCourse().getName());
        mentorDto.setGroupName(groupRepository.findGroupsNameByMentorId(id));

        return mentorDto;

    }

    @Override
    public List<Mentor> getBlacklist() {
        return mentorRepository.findAllByStatus(Status.BLACKLIST);
    }

    public Mentor getMentorEntityById(Long mentorId) {
        return mentorRepository.findById(mentorId)
                .orElseThrow(
                        () -> new EntityNotFoundException(EntityEnum.MENTOR, "id", mentorId)
                );
    }

    private ExampleMatcher getExampleMatcherForCards() {
        return ExampleMatcher.matchingAll()
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("course", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id");
    }
}
