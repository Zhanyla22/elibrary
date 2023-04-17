package com.example.neolabs.mapper;

import com.example.neolabs.dto.MentorCardDto;
import com.example.neolabs.dto.MentorDto;
import com.example.neolabs.dto.request.create.CreateMentorRequest;
import com.example.neolabs.entity.Mentor;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MentorMapper {

    final CourseRepository courseRepository;

    public static MentorDto entityToMentorDto(Mentor mentor){
        return MentorDto.builder()
                .id(mentor.getId())
                .firstName(mentor.getFirstName())
                .lastName(mentor.getLastName())
                .email(mentor.getEmail())
                .imageUrl(mentor.getImageUrl())
                .courseName(mentor.getCourse().getName())
                .phoneNumber(mentor.getPhoneNumber())
                .build();
    }

    public static MentorCardDto entityToMentorCardDto(Mentor mentor) {
        MentorCardDto mentorCardDto = new MentorCardDto();
        mentorCardDto.setId(mentor.getId());
        mentorCardDto.setEmail(mentor.getEmail());
        mentorCardDto.setFirstName(mentor.getFirstName());
        mentorCardDto.setLastName(mentor.getLastName());
        mentorCardDto.setImageUrl(mentor.getImageUrl());
        mentorCardDto.setCourse(mentor.getCourse().getName());
        mentorCardDto.setDateArchive(mentor.getUpdatedDate());
        mentorCardDto.setReasonArchive(mentor.getReason());
        return mentorCardDto;
    }

    public static List<MentorCardDto> entityListToMentorCardDtoList(List<Mentor> mentors){
        return mentors.stream().map(MentorMapper::entityToMentorCardDto).collect(Collectors.toList());
    }

    public Mentor createMentorDtoToMentorEntity(CreateMentorRequest createMentorRequest) {
        Mentor mentor = new Mentor();
        mentor.setEmail(createMentorRequest.getEmail());
        mentor.setFirstName(createMentorRequest.getFirstName());
        mentor.setLastName(createMentorRequest.getLastName());
        mentor.setPhoneNumber(createMentorRequest.getPhoneNumber());
        mentor.setCourse(courseRepository.findById(createMentorRequest.getCourseId())
                .orElseThrow(
                        () -> new BaseException("Course with id" + createMentorRequest.getCourseId() + " not found", HttpStatus.NOT_FOUND)));
        mentor.setStatus(Status.ACTIVE);
        return mentor;
    }

    public List<MentorCardDto> entityListToCardList(List<Mentor> mentors) {
        return mentors.stream().map(MentorMapper::entityToMentorCardDto).collect(Collectors.toList());
    }
}
