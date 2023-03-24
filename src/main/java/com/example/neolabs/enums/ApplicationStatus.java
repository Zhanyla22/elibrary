package com.example.neolabs.enums;

public enum ApplicationStatus {
    WAITING_FOR_CALL{
        @Override
        public int getOrder(){
            return 1;
        }
    },
    CALL_RECEIVED{
        @Override
        public int getOrder(){
            return 2;
        }
    },
    APPLIED_FOR_TRIAL{
        @Override
        public int getOrder(){
            return 3;
        }
    },
    ATTENDED_TRIAL{
        @Override
        public int getOrder(){
            return 4;
        }
    },
    APPLIED_FOR_COURSES{
        @Override
        public int getOrder(){
            return 5;
        }
    },
    DID_NOT_APPLY_FOR_COURSES{
        @Override
        public int getOrder(){
            return 6;
        }
    },
    GOT_REJECTED{
        @Override
        public int getOrder(){
            return 7;
        }
    };


    public int getOrder(){
        return 0;
    }
}
