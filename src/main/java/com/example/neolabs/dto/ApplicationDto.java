package com.example.neolabs.dto;

import com.example.neolabs.enums.ApplicationStatus;
import com.example.neolabs.enums.Education;
import com.example.neolabs.enums.Gender;
import com.example.neolabs.enums.MarketingStrategyEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    Long id;

    @JsonProperty(value = "first_name")
    String firstName;

    @JsonProperty(value = "last_name")
    String lastName;

    @JsonProperty(value = "email")
    String email;

    @JsonProperty(value = "phone_number")
    String phoneNumber;

    @JsonProperty(value = "gender")
    Gender gender;

    @JsonProperty(value = "has_laptop")
    Boolean hasLaptop;

//    @JsonProperty(value = "marketing_strategy", access = JsonProperty.Access.READ_ONLY)
//    MarketingStrategy marketingStrategy;
//
//    @JsonProperty(value = "marketing_strategy_id")
//    Long marketingStrategyId;

    @JsonProperty(value = "marketing_strategy")
    MarketingStrategyEnum marketingStrategyEnum;

    @JsonProperty(value = "department", access = JsonProperty.Access.READ_ONLY)
    DepartmentDTO departmentDTO;

    @JsonProperty(value = "department_id", access = JsonProperty.Access.WRITE_ONLY)
    Long departmentId;

    @JsonProperty(value = "is_archived", access = JsonProperty.Access.READ_ONLY)
    Boolean isArchived;

    @JsonProperty(value = "reason")
    String reason;

    @JsonProperty(value = "application_status_name", access = JsonProperty.Access.READ_ONLY)
    ApplicationStatus applicationStatus;

    @JsonProperty(value = "application_initial_status", access = JsonProperty.Access.WRITE_ONLY)
    Integer applicationStatusNum;

    @JsonProperty(value = "application_status_update_time", access = JsonProperty.Access.READ_ONLY)
    LocalDateTime applicationStatusUpdateDate;

    @JsonProperty(value = "creation_time", access = JsonProperty.Access.READ_ONLY)
    LocalDateTime creationDate;

    @JsonProperty(value = "update_time", access = JsonProperty.Access.READ_ONLY)
    LocalDateTime updateDate;

    @JsonProperty(value = "education")
    Education education;

    @JsonProperty(value = "is_urgent", access = JsonProperty.Access.READ_ONLY)
    Boolean isUrgent;
}
