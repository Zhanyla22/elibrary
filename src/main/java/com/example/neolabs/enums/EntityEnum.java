package com.example.neolabs.enums;

public enum EntityEnum {

    USER{
        @Override
        public String getRussian(){
            return "Пользователь";
        }
    },
    APPLICATION{
        @Override
        public String getRussian(){
            return "Заявка";
        }
    },
    GROUP{
        @Override
        public String getRussian(){
            return "Группа";
        }
    },
    STUDENT{
        @Override
        public String getRussian(){
            return "Студент";
        }
    },
    COURSE{
        @Override
        public String getRussian(){
            return "Курс";
        }
    },
    MENTOR{
        @Override
        public String getRussian(){
            return "Ментор";
        }
    },
    SCHEDULE{
        @Override
        public String getRussian(){
            return "Расписание";
        }
    },
    PAYMENT{
        @Override
        public String getRussian(){
            return "Оплата";
        }
    },
    MONTHLY_BILL,
    SCHEDULE_UNIT,
    STUDENT_GROUP_BILL,

    APPLICATION_OPERATION,
    USER_OPERATION,
    GROUP_OPERATION,
    COURSE_OPERATION,
    DEPARTMENT_OPERATION,
    MENTOR_OPERATION,
    STUDENT_OPERATION,
    PAYMENT_OPERATION;
    public String getRussian(){
        return "string";
    }
}
