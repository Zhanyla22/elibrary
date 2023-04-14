package com.example.neolabs.dto.request.create;

import com.example.neolabs.enums.Gender;
import com.example.neolabs.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateStudentRequest {

    @NotBlank
    @Email
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String email;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String firstName;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String lastName;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String phoneNumber;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Gender gender;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Status status;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Long enrollmentGroupId;

}
