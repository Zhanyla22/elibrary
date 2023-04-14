package com.example.neolabs.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForgotPasswordCodeRequestDto {

    String code;

    @NotNull
    String newPassword;
}
