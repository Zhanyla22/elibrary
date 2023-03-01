package com.example.neolabs.controller.base;

import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.enums.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    protected <T> ResponseEntity<ResponseDto> constructSuccessResponse(T result) {
        return new ResponseEntity<>(
                ResponseDto.builder()
                        .result(result)
                        .resultCode(ResultCode.SUCCESS)
                        .build(),
                HttpStatus.OK
        );
    }

    protected <T> ResponseEntity<ResponseDto> constructSuccessResponse(T result, String details) {
        return new ResponseEntity<>(
                ResponseDto.builder()
                        .result(result)
                        .resultCode(ResultCode.SUCCESS)
                        .details(details)
                        .build(),
                HttpStatus.OK
        );
    }

    protected <T> ResponseEntity<ResponseDto> constructFailResponse(String details) {
        return new ResponseEntity<>(
                ResponseDto.builder()
                        .resultCode(ResultCode.FAIL)
                        .details(details)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    protected <T> ResponseEntity<ResponseDto> constructFailResponse(T errorResult, String details) {
        return new ResponseEntity<>(
                ResponseDto.builder()
                        .result(errorResult)
                        .resultCode(ResultCode.FAIL)
                        .details(details)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

}
