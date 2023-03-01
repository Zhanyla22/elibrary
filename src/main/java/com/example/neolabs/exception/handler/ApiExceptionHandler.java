package com.example.neolabs.exception.handler;

import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.enums.ResultCode;
import com.example.neolabs.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ApiExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ResponseDto> getExceptionMessage(final BaseException e) {
        return buildBaseResponseMessage(e.getMessage(), e.getStatus());
    }

    private ResponseEntity<ResponseDto> buildBaseResponseMessage(String message, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDto.builder()
                        .resultCode(ResultCode.EXCEPTION)
                        .details(message)
                        .build(),
                status
        );
    }

}
