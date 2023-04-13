package com.example.neolabs.dto;

import com.example.neolabs.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupDto {

    @JsonProperty(value = "id")
    Long id;

    @JsonProperty(value = "course")
    CourseDto course;

    @JsonProperty(value = "maxCapacity")
    Integer maxCapacity;

    @JsonProperty(value = "mentor")
    MentorCardDto mentor;

    @JsonProperty(value = "startDate")
    LocalDate startDate;

    @JsonProperty(value = "endDate")
    LocalDate endDate;

    @JsonProperty(value = "status")
    Status status;

    @JsonProperty(value = "isArchived")
    Boolean isArchived;
}