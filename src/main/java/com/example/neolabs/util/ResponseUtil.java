package com.example.neolabs.util;

import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.enums.ResultCode;

public class ResponseUtil {
    public static ResponseDto buildSuccessResponse(Object result){
        return ResponseDto.builder()
                .resultCode(ResultCode.SUCCESS)
                .result(result)
                .build();
    }

    public static ResponseDto buildSuccessResponse(Object result, String details){
        return ResponseDto.builder()
                .resultCode(ResultCode.SUCCESS)
                .result(result)
                .details(details)
                .build();
    }
}
