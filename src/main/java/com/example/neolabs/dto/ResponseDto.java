package com.example.neolabs.dto;

import com.example.neolabs.enums.ResultCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseDto {

    Object result;

    ResultCode resultCode;

    String details;
}
