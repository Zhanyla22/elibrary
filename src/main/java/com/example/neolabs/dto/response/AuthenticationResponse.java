package com.example.neolabs.dto.response;

import com.example.neolabs.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {

    String jwtToken;

    Date dateExpiredAccessToken;

    Date dateExpiredRefreshToken;

    String refreshToken;

}
