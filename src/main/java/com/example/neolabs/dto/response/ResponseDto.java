package com.example.neolabs.dto.response;

import com.example.neolabs.enums.ResultCode;
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
