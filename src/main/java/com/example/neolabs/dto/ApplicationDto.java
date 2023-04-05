package com.example.neolabs.dto;

import com.example.neolabs.entity.MarketingStrategy;
import com.example.neolabs.enums.ApplicationStatus;
import com.example.neolabs.enums.Education;
import com.example.neolabs.enums.Gender;
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

    @JsonProperty(value = "firstName")
    String firstName;

    @JsonProperty(value = "lastName")
    String lastName;

    @JsonProperty(value = "email")
    String email;

    @JsonProperty(value = "phoneNumber")
    String phoneNumber;

    @JsonProperty(value = "gender")
    Gender gender;

    @JsonProperty(value = "hasLaptop")
    Boolean hasLaptop;

    @JsonProperty(value = "marketingStrategy", access = JsonProperty.Access.READ_ONLY)
    MarketingStrategy marketingStrategy;

    @JsonProperty(value = "marketingStrategyId")
    Long marketingStrategyId;

    @JsonProperty(value = "isArchived", access = JsonProperty.Access.READ_ONLY)
    Boolean isArchived;

    @JsonProperty(value = "reason")
    String reason;

    @JsonProperty(value = "applicationStatusName", access = JsonProperty.Access.READ_ONLY)
    ApplicationStatus applicationStatus;

    @JsonProperty(value = "applicationStatusOrder")
    Integer applicationStatusOrder;

    @JsonProperty(value = "applicationStatusUpdateTime", access = JsonProperty.Access.READ_ONLY)
    LocalDateTime applicationStatusUpdateDate;

    @JsonProperty(value = "creationTime", access = JsonProperty.Access.READ_ONLY)
    LocalDateTime creationDate;

    @JsonProperty(value = "updateTime", access = JsonProperty.Access.READ_ONLY)
    LocalDateTime updateDate;

    @JsonProperty(value = "education")
    Education education;

    @JsonProperty(value = "isUrgent", access = JsonProperty.Access.READ_ONLY)
    Boolean isUrgent;
}
