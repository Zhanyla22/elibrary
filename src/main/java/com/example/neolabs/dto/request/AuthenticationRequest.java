package com.example.neolabs.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {

    @Email
    String email;

    String password;
}
