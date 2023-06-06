package com.example.neolabs.dto.response;

import com.example.neolabs.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse2RoleResponse {

    AuthenticationResponse authenticationResponse;

    Role role;

    String firstName;

    String lastName;

    String phoneNumber;

    String course;

}
