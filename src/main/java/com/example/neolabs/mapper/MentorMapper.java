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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        return MentorCardDto.builder()
                .id(mentor.getId())
                .email(mentor.getEmail())
                .firstName(mentor.getFirstName())
                .lastName(mentor.getLastName())
                .imageUrl(mentor.getImageUrl())
                .course(mentor.getCourse().getName())
                .dateArchive(mentor.getUpdatedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                .reasonArchive(mentor.getReason())
                .build();
    }

    public static List<MentorCardDto> entityListToMentorCardDtoList(List<Mentor> mentors){
        return mentors.stream().map(MentorMapper::entityToMentorCardDto).collect(Collectors.toList());
    }

    public Mentor createMentorDtoToMentorEntity(CreateMentorRequest createMentorRequest) {
        return Mentor.builder()
                .email(createMentorRequest.getEmail())
                .firstName(createMentorRequest.getFirstName())
                .lastName(createMentorRequest.getLastName())
                .phoneNumber(createMentorRequest.getPhoneNumber())
                .course(courseRepository.findById(createMentorRequest.getCourseId())
                        .orElseThrow(
                                () -> new BaseException("Course with id" + createMentorRequest.getCourseId() + " not found", HttpStatus.NOT_FOUND)))
                .status(Status.ACTIVE)
                .build();
    }

    public List<MentorCardDto> entityListToCardList(List<Mentor> mentors) {
        return mentors.stream().map(MentorMapper::entityToMentorCardDto).collect(Collectors.toList());
    }
}
