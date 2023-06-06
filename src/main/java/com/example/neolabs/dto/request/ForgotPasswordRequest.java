package com.example.neolabs.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForgotPasswordRequest {

    @NotNull
    @Email
    String email;
}
