package com.example.neolabs.dto.request.update;

import com.example.neolabs.enums.Education;
import com.example.neolabs.enums.Gender;
import com.example.neolabs.enums.MarketingStrategyEnum;
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
public class UpdateApplicationRequest {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String firstName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String lastName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Gender gender;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Boolean hasLaptop;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    MarketingStrategyEnum marketingStrategyEnum;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Long courseId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String reason;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Integer applicationStatusInitialNum;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Education education;
}
