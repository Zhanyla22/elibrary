package com.example.neolabs.exception;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@Data
@ResponseStatus(HttpStatus.BAD_REQUEST)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomException extends RuntimeException {

    final String message = "Something went wrong";
    final Integer statusCode = 400;
}

