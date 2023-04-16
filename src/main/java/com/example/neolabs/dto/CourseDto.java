package com.example.neolabs.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDto {
    Long id;

    String name;

    Integer cost;

    Boolean isArchived;

    Integer durationInMonth;

    Integer numberOfLessons;

    Integer numberOfStudents;

    Integer numberOfMentors;

    Integer numberOfGroups;

    String imageUrl;

    List<GroupStudentsDto> groupStudents;
}
