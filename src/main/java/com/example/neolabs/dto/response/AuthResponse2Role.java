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
public class AuthResponse2Role {

    AuthenticationResponse authenticationResponse;

    Role role;
}
