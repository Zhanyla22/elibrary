package com.example.neolabs.dto.request.create;

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
public class CreateApplicationRequest {

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String firstName;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String lastName;

    @Email
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String email;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String phoneNumber;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Gender gender;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Boolean hasLaptop;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    MarketingStrategyEnum marketingStrategyEnum;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Long departmentId;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String reason;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Integer applicationStatusInitialNum;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Education education;
}
