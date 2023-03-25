package com.example.neolabs.dto.request;


import com.example.neolabs.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequest {

    @JsonProperty(value = "email")
    String email;

    @JsonProperty(value = "password")
    String password;

//    Role role;

    @JsonProperty(value = "phone_number")
    String phoneNumber;

    @JsonProperty(value = "first_name")
    String firstName;

    @JsonProperty(value = "last_name")
    String lastName;
}
