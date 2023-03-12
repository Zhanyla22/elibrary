package com.example.neolabs.exception;

import com.example.neolabs.enums.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntityNotFoundException extends BaseException{

    public EntityNotFoundException(Entity entity, String attribute, String attributeValue) {
        super(String.format("%s with %s %s was not found.", entity.toString(), attribute, attributeValue),
                HttpStatus.NOT_FOUND);
    }
}
