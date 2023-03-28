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

    @JsonProperty(value = "max_capacity")
    Integer maxCapacity;

    @JsonProperty(value = "mentor")
    MentorDto mentor;

    @JsonProperty(value = "start_date")
    LocalDate startDate;

    @JsonProperty(value = "end_date")
    LocalDate endDate;

    @JsonProperty(value = "status")
    Status status;

    @JsonProperty(value = "is_archived")
    Boolean isArchived;
}