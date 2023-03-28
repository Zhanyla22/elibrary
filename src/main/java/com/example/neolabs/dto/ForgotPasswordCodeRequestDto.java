package com.example.neolabs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForgotPasswordCodeRequestDto {

    @JsonProperty(value = "code")
    String code;

    @JsonProperty(value = "new_password")
    @NotNull
    String newPassword;
}
