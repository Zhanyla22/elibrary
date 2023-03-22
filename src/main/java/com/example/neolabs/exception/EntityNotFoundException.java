package com.example.neolabs.exception;

import com.example.neolabs.enums.EntityEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(EntityEnum entityEnum, String attribute, Object attributeValue) {
        super(String.format("%s with %s %s was not found.", entityEnum.toString(), attribute, attributeValue),
                HttpStatus.NOT_FOUND);
    }
}
