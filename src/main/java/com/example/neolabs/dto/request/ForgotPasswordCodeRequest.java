package com.example.neolabs.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForgotPasswordCodeRequest {

    String code;

    @NotNull
    String newPassword;
}
