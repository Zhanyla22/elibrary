package com.example.neolabs.dto.request.update;

import com.example.neolabs.enums.Gender;
import com.example.neolabs.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateStudentRequest {

    @Email
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String firstName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String lastName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String phoneNumber;

}
