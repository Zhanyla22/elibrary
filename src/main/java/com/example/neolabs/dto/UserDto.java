package com.example.neolabs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    @NotEmpty
    @Email
    @JsonProperty(value = "email")
    String email;

    @JsonProperty(value = "phone_number")
    String phoneNumber;

    @JsonProperty(value = "first_name")
    String firstName;

    @JsonProperty(value = "last_name")
    String lastName;

    @JsonProperty(value = "last_visit_date")
    String lastVisitDate;

    @JsonProperty(value = "last_visit_time")
    String lastVisitTime;
}
