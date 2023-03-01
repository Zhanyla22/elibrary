package com.example.neolabs.dto.registration;


import com.example.neolabs.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequest {
    String email;
    String password;
    Role role;
    String phoneNumber;
    String firstName;
    String lastName;
}
