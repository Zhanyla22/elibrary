package com.example.neolabs.exception.handler;

import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.enums.ResultCode;
import com.example.neolabs.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
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
