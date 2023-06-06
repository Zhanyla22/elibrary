package com.example.neolabs.dto.request;


import com.example.neolabs.enums.Group;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequest {

    String login;

    String password;

    String phoneNumber;

    String firstName;

    String lastName;

    Long courseId;
}
