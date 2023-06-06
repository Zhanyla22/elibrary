package com.example.neolabs.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Status {

     ACTIVE("ACTIVE"){
        @Override
        public String getRussian(){
            return "Активный";
        }
    },
    DELETED("DELETED"){
        @Override
        public String getRussian(){
            return "Удален";
        }
    },
    ARCHIVED("ARCHIVED"){
        @Override
        public String getRussian(){
            return "Архивирован";
        }
    },
    FROZEN("FROZEN"){
        @Override
        public String getRussian(){
            return "Заморожен";
        }
    },
    OLD("OLD"){
        @Override
        public String getRussian(){return "Старая книга";}
    },
    BOOKED("BOOKED"){
        @Override
        public String getRussian(){
            return "забронирована";
        }
    },
    LOAN("LOAN"){
        @Override
        public String getRussian(){
            return "на руках";
        }
    };

    final String name;
    public String getRussian() {return "Статус";}
}
