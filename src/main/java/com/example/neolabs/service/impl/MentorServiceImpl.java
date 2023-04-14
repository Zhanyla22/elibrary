package com.example.neolabs.service.impl;

import com.example.neolabs.dto.ArchiveDto;
import com.example.neolabs.dto.request.create.CreateMentorRequest;
import com.example.neolabs.dto.MentorCardDto;
import com.example.neolabs.dto.UpdateMentorDto;
import com.example.neolabs.entity.Course;
import com.example.neolabs.entity.Mentor;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.MentorMapper;
import com.example.neolabs.repository.CourseRepository;
import com.example.neolabs.repository.MentorRepository;
import com.example.neolabs.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

    final MentorRepository mentorRepository;
    final MentorMapper mentorMapper;
    // TODO: 14.04.2023 if possible its better to NOT use other repos in service class
    final CourseRepository courseRepository;
    final CourseServiceImpl courseService;

    @Override
    public List<MentorCardDto> getAllMentorCard(Long courseId, Status status) {
        Course course = courseService.getCourseEntityById(courseId);
        List<Mentor> mentors = mentorRepository.findAllByCourseAndStatus(course, status);
        List<MentorCardDto> mentorCardDtos = new ArrayList<>();

        mentors.forEach(x -> mentorCardDtos.add(MentorMapper.mentorEntityToMentorCardDto(x)));

        return mentorCardDtos;
    }

    @Override
    public void addNewMentor(CreateMentorRequest createMentorRequest) {
        if (!mentorRepository.existsByEmail(createMentorRequest.getEmail())) {
            mentorRepository.save(mentorMapper.createMentorDtoToMentorEntity(createMentorRequest));
        } else
            throw new BaseException("mentor with email " + createMentorRequest.getEmail() + " already exists", HttpStatus.BAD_REQUEST);
    }

    @Override
    public void deleteMentorById(Long id) {
        Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(
                        () -> new BaseException("mentor with id " + id + " not found", HttpStatus.BAD_REQUEST));
        mentor.setStatus(Status.DELETED);
        mentor.setDeletedDate(LocalDateTime.now());
        mentorRepository.save(mentor);
    }

    @Override
    public void updateMentorById(UpdateMentorDto updateMentorDto, Long id) {
        Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(
                        () -> new BaseException("mentor with id " + id + " not found ", HttpStatus.BAD_REQUEST)
                ); // TODO: 14.04.2023 wouldnt it be better to use new function getMentorEntityById like i made as a draft in 2 functions below? 
        mentor.setEmail(updateMentorDto.getEmail());
        mentor.setFirstName(updateMentorDto.getFirstName());
        mentor.setLastName(updateMentorDto.getLastName());
        mentor.setPhoneNumber(updateMentorDto.getPhoneNumber());
        mentor.setPatentNumber(updateMentorDto.getPatentNumber());
        mentor.setSalary(updateMentorDto.getSalary());
        mentor.setCourse(courseRepository.findCourseByName(updateMentorDto.getCourseName())
                .orElseThrow(
                        () -> new BaseException("course " + updateMentorDto.getCourseName() + " not found", HttpStatus.BAD_REQUEST)));
        mentorRepository.save(mentor);
    }

    @Override
    public void archiveMentorById(Long mentorId, ArchiveDto mentorArchiveDto) {
        Mentor mentor = getMentorEntityById(mentorId);// TODO: 14.04.2023 here 
        mentor.setUpdatedDate(LocalDateTime.now());
        mentor.setReason(mentorArchiveDto.getReason());
        mentor.setStatus(Status.ARCHIVED);

        mentorRepository.save(mentor);
    }

    @Override
    public void blackListMentorById(Long mentorId, ArchiveDto mentorBlacklistDto) {
        Mentor mentor = getMentorEntityById(mentorId);// TODO: 14.04.2023 and here 
        mentor.setUpdatedDate(LocalDateTime.now());
        mentor.setReason(mentorBlacklistDto.getReason());
        mentor.setStatus(Status.BLACK_LIST);

        mentorRepository.save(mentor);
    }

    public Mentor getMentorEntityById(Long mentorId){
        return mentorRepository.findById(mentorId)
                .orElseThrow(
                        () -> new EntityNotFoundException(EntityEnum.MENTOR, "id", mentorId)
                );
    }
}
