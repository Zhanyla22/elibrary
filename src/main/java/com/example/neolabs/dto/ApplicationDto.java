package com.example.neolabs.dto;

import com.example.neolabs.enums.ApplicationStatus;
import com.example.neolabs.enums.Education;
import com.example.neolabs.enums.Gender;
import com.example.neolabs.enums.MarketingStrategyEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    Long id;

    @JsonProperty(value = "firstName")
    String firstName;

    @JsonProperty(value = "lastName")
    String lastName;

    @NotEmpty
    @Email
    @JsonProperty(value = "email")
    String email;

    @JsonProperty(value = "phoneNumber")
    String phoneNumber;

    @JsonProperty(value = "gender")
    Gender gender;

    @JsonProperty(value = "hasLaptop")
    Boolean hasLaptop;

//    @JsonProperty(value = "marketingStrategy", access = JsonProperty.Access.READ_ONLY)
//    MarketingStrategy marketingStrategy;
//
//    @JsonProperty(value = "marketingStrategyId")
//    Long marketingStrategyId;

    @JsonProperty(value = "marketingStrategy")
    MarketingStrategyEnum marketingStrategyEnum;

    @JsonProperty(value = "department", access = JsonProperty.Access.READ_ONLY)
    DepartmentDto departmentDTO;

    @JsonProperty(value = "departmentId", access = JsonProperty.Access.WRITE_ONLY)
    Long departmentId;

    @JsonProperty(value = "isArchived", access = JsonProperty.Access.READ_ONLY)
    Boolean isArchived;

    @JsonProperty(value = "reason")
    String reason;

    @JsonProperty(value = "applicationStatusName", access = JsonProperty.Access.READ_ONLY)
    ApplicationStatus applicationStatus;

    @JsonProperty(value = "applicationStatusNum", access = JsonProperty.Access.READ_ONLY)
    Integer applicationStatusNum;

    @JsonProperty(value = "applicationInitialStatusNum", access = JsonProperty.Access.WRITE_ONLY)
    Integer applicationStatusInitialNum;

    @JsonProperty(value = "applicationStatusUpdateDate", access = JsonProperty.Access.READ_ONLY)
    String applicationStatusUpdateDate;

    @JsonProperty(value = "applicationStatusUpdateTime", access = JsonProperty.Access.READ_ONLY)
    String applicationStatusUpdateTime;

    @JsonProperty(value = "creationDate", access = JsonProperty.Access.READ_ONLY)
    String creationDate;

    @JsonProperty(value = "updateDate", access = JsonProperty.Access.READ_ONLY)
    String updateDate;

    @JsonProperty(value = "education")
    Education education;

    @JsonProperty(value = "isUrgent", access = JsonProperty.Access.READ_ONLY)
    Boolean isUrgent;
}
