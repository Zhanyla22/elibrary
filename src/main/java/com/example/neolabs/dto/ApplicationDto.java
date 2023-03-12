package com.example.neolabs.dto;

import com.example.neolabs.entity.MarketingStrategy;
import com.example.neolabs.enums.ApplicationStatus;
import com.example.neolabs.enums.Education;
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

    Long id;

    @JsonProperty(value = "first_name")
    String firstName;

    @JsonProperty(value = "last_name")
    String lastName;

    String email;

    @JsonProperty(value = "phone_number")
    String phoneNumber;

    @JsonProperty(value = "has_laptop")
    Boolean hasLaptop;

    @JsonProperty(value = "marketing_strategy")
    MarketingStrategy marketingStrategy;

    @JsonProperty(value = "is_archived")
    Boolean isArchived;

    String reason;

    @JsonProperty(value = "application_status")
    ApplicationStatus applicationStatus;

    @JsonProperty(value = "application_status_update_time")
    LocalDateTime applicationStatusUpdateDate;

    @JsonProperty(value = "creation_time")
    LocalDateTime creationDate;

    @JsonProperty(value = "update_time")
    LocalDateTime updateDate;

    Education education;
}
