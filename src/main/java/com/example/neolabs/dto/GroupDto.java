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

    Long id;

    String name;

    String courseName;

    Integer maxCapacity;

    MentorCardDto mentor;

    LocalDate startDate;

    LocalDate endDate;

    String archiveDate;

    String archiveReason;

    Boolean isArchived;

    Status status;
}