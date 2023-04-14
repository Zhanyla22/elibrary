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

    @NotEmpty
    @Email
    @JsonProperty("email")
    String email;

    @NotBlank
    @JsonProperty("firstName")
    String firstName;

    @NotBlank
    @JsonProperty("lastName")
    String lastName;

    @NotBlank
    @JsonProperty("phoneNumber")
    String phoneNumber;

    @NotBlank
    @JsonProperty("gender")
    Gender gender;

    @JsonProperty("status")
    Status status;

    @JsonProperty(value = "totalDebt", access = JsonProperty.Access.READ_ONLY)
    Integer totalDebt;

    @JsonProperty(value = "paymentPercentage", access = JsonProperty.Access.READ_ONLY)
    Integer totalPaymentPercentage;

    @JsonProperty(value = "groups", access = JsonProperty.Access.READ_ONLY)
    List<GroupDto> groups;

    @NotBlank
    @JsonProperty(value = "enrollmentGroupId", access = JsonProperty.Access.WRITE_ONLY)
    Long enrollmentGroupId;
}
