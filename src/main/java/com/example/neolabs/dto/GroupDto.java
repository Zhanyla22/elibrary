package com.example.neolabs.dto;

import com.example.neolabs.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    CourseCardDto course;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Integer maxCapacity;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    MentorCardDto mentor;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDate startDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String imageUrl;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDate endDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Status status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<StudentDto> students;
}