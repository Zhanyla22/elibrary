package com.example.neolabs.dto.authentication;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AuthenticationResponse {

    String jwtToken;

    //TODO: implement refresh Token
    String refreshToken;

}
