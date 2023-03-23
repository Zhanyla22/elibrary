package com.example.neolabs.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Status {
    //TODO: пока что так(черновой вариант) потом добавим еще
    //TODO: status types for now need to be added new status or changed
    ACTIVE("active"),
    DELETED("deleted"),
    ARCHIVED("archived");

    final String name;
}
