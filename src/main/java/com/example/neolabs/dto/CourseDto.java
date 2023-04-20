package com.example.neolabs.dto;


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

    String archiveDate;

    String archiveReason;

    Integer durationInMonth;

    Integer numberOfLessons;

    Integer numberOfStudents;

    Integer numberOfMentors;

    Integer numberOfGroups;

    String imageUrl;

    List<GroupStudentsDto> groups;

    List<MentorCardDto> mentors;
}
