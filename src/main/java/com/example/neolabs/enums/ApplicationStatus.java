package com.example.neolabs.enums;

public enum ApplicationStatus {
    WAITING_FOR_CALL{
        @Override
        int getOrder(){
            return 1;
        }
    },
    CALL_RECEIVED{
        @Override
        int getOrder(){
            return 2;
        }
    },
    APPLIED_FOR_TRIAL{
        @Override
        int getOrder(){
            return 3;
        }
    },
    ATTENDED_TRIAL{
        @Override
        int getOrder(){
            return 4;
        }
    },
    APPLIED_FOR_COURSES{
        @Override
        int getOrder(){
            return 5;
        }
    },
    DID_NOT_APPLY_FOR_COURSES{
        @Override
        int getOrder(){
            return 6;
        }
    },
    GOT_REJECTED{
        @Override
        int getOrder(){
            return 7;
        }
    };


    int getOrder(){
        return 0;
    }
}
