package com.example.neolabs.dto;

import com.example.neolabs.enums.Gender;
import com.example.neolabs.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String firstName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String lastName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Gender gender;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Integer totalDebt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Integer totalPaymentPercentage;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<GroupDto> groups;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long enrollmentGroupId;
}
