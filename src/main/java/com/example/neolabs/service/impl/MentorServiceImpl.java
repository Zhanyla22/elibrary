package com.example.neolabs.service.impl;

import com.example.neolabs.dto.CreateMentorDto;
import com.example.neolabs.dto.ArchiveDto;
import com.example.neolabs.dto.MentorCardDto;
import com.example.neolabs.dto.UpdateMentorDto;
import com.example.neolabs.entity.Mentor;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.mapper.MentorMapper;
import com.example.neolabs.repository.DepartmentRepository;
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
    final DepartmentRepository departmentRepository;
    final MentorMapper mentorMapper;

    @Override
    public List<MentorCardDto> getAllMentorCard(Long departmentId, Status status) {
        List<Mentor> mentors = mentorRepository.findAllByDepartmentAndStatus(
                departmentRepository.findById(departmentId)
                        .orElseThrow(
                                () -> new BaseException("department wit id " + departmentId + " not found", HttpStatus.BAD_REQUEST)
                        )
                , status);
        List<MentorCardDto> mentorCardDtos = new ArrayList<>();

        mentors.forEach(x -> mentorCardDtos.add(MentorMapper.mentorEntityToMentorCardDto(x)));

        return mentorCardDtos;
    }

    @Override
    public void addNewMentor(CreateMentorDto createMentorDto) {
        if (!mentorRepository.existsByEmail(createMentorDto.getEmail())) {
            mentorRepository.save(mentorMapper.createMentorDtoToMentorEntity(createMentorDto));
        } else
            throw new BaseException("mentor with email " + createMentorDto.getEmail() + " already exists", HttpStatus.BAD_REQUEST);
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
                );
        mentor.setEmail(updateMentorDto.getEmail());
        mentor.setFirstName(updateMentorDto.getFirstName());
        mentor.setLastName(updateMentorDto.getLastName());
        mentor.setPhoneNumber(updateMentorDto.getPhoneNumber());
        mentor.setPatentNumber(updateMentorDto.getPatentNumber());
        mentor.setSalary(updateMentorDto.getSalary());
        mentor.setDepartment(departmentRepository.getDepartmentByName(updateMentorDto.getDepartmentName())
                .orElseThrow(
                        () -> new BaseException("department " + updateMentorDto.getDepartmentName() + " not found", HttpStatus.BAD_REQUEST)));
        mentorRepository.save(mentor);
    }

    @Override
    public void archiveMentorById(Long id, ArchiveDto mentorArchiveDto) {
        Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(
                        () -> new BaseException("mentor with id " + id + " not found", HttpStatus.BAD_REQUEST));
        mentor.setDateArchive(LocalDateTime.now());
        mentor.setReasonArchive(mentorArchiveDto.getReason());
        mentor.setStatus(Status.ARCHIVED);

        mentorRepository.save(mentor);
    }

    @Override
    public void blackListMentorById(Long id, ArchiveDto mentorArchiveDto) {
        Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(
                        () -> new BaseException("mentor with id " + id + " not found", HttpStatus.BAD_REQUEST));
        mentor.setDateArchive(LocalDateTime.now());
        mentor.setReasonArchive(mentorArchiveDto.getReason());
        mentor.setStatus(Status.BLACK_LIST);

        mentorRepository.save(mentor);
    }
}
