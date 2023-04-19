package com.example.neolabs.dto;

import com.example.neolabs.enums.ApplicationStatus;
import com.example.neolabs.enums.Education;
import com.example.neolabs.enums.Gender;
import com.example.neolabs.enums.MarketingStrategyEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationDto {

    Long id;

    String firstName;

    String lastName;

    String email;

    String phoneNumber;

    String gender;

    Boolean hasLaptop;

    MarketingStrategyEnum marketingStrategyEnum;

    Boolean isArchived;

    String reason;

    ApplicationStatus applicationStatus;

    Integer applicationStatusNum;

    String applicationStatusUpdateDate;

    String applicationStatusUpdateTime;

    String creationDate;

    String updateDate;

    Education education;

    Boolean isUrgent;

    CourseCardDto courseCardDto;
}
