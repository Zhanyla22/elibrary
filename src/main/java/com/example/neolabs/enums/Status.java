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
    ACTIVE("active"){
        @Override
        public String getRussian(){
            return "Активный";
        }
    },
    DELETED("deleted"){
        @Override
        public String getRussian(){
            return "Удален";
        }
    },
    ARCHIVED("archived"){
        @Override
        public String getRussian(){
            return "Архивирован";
        }
    },
    BLACKLIST("blacklist"){
        @Override
        public String getRussian(){
            return "В черном списке";
        }
    };

    final String name;
    public String getRussian() {return "Статус";}
}
