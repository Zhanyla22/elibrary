package com.example.neolabs.mapper;

import com.example.neolabs.dto.CreateMentorDto;
import com.example.neolabs.dto.MentorCardDto;
import com.example.neolabs.entity.Mentor;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MentorMapper {

    final DepartmentRepository departmentRepository;

    public static MentorCardDto mentorEntityToMentorCardDto(Mentor mentor) {
        MentorCardDto mentorCardDto = new MentorCardDto();
        mentorCardDto.setEmail(mentor.getEmail());
        mentorCardDto.setFirstName(mentor.getFirstName());
        mentorCardDto.setLastName(mentor.getLastName());
        mentorCardDto.setImageUrl(mentor.getImageUrl());
        mentorCardDto.setDepartment(mentor.getDepartment().getName());
        mentorCardDto.setDateArchive(mentor.getDateArchive());
        mentorCardDto.setReasonArchive(mentor.getReasonArchive());
        return mentorCardDto;
    }

    public Mentor createMentorDtoToMentorEntity(CreateMentorDto createMentorDto) {
        Mentor mentor = new Mentor();
        mentor.setEmail(createMentorDto.getEmail());
        mentor.setFirstName(createMentorDto.getFirstName());
        mentor.setLastName(createMentorDto.getLastName());
        mentor.setPhoneNumber(createMentorDto.getPhoneNumber());
        mentor.setDepartment(departmentRepository.getDepartmentByName(createMentorDto.getDepartmentName())
                .orElseThrow(
                        () -> new BaseException("department " + createMentorDto.getDepartmentName() + " not found", HttpStatus.BAD_REQUEST)));
        mentor.setStatus(Status.ACTIVE);
        return mentor;
    }
}
