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

    @JsonProperty(value = "result")
    Object result;

    @JsonProperty(value = "result_code")
    ResultCode resultCode;

    @JsonProperty(value = "details")
    String details;
}
