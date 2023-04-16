package com.example.neolabs.enums;

public enum Gender {

    MALE{
        @Override
        public String getRussian(){
            return "М";
        }
    },
    FEMALE{
        @Override
        public String getRussian(){
            return "Ж";
        }
    },
    UNKNOWN;

    public String getRussian(){
        return "Пол";
    }
}
