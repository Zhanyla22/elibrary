package com.example.neolabs.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForgotPasswordRequestDto {

    @NotNull
    @Email
    String email;

}
