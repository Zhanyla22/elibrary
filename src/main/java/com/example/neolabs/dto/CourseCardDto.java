package com.example.neolabs.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseCardDto {
    Long id;

    String name;

    Integer cost;

    Integer durationInMonth;

    Integer numberOfLessons;

    Integer numberOfGroups;

    String imageUrl;
}
