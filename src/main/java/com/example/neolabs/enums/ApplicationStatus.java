package com.example.neolabs.enums;

public enum ApplicationStatus {
    WAITING_FOR_CALL{
        @Override
        int order(){
            return 1;
        }
    },
    CALL_RECEIVED{
        @Override
        int order(){
            return 2;
        }
    },
    APPLIED_FOR_TRIAL{
        @Override
        int order(){
            return 3;
        }
    },
    ATTENDED_TRIAL{
        @Override
        int order(){
            return 4;
        }
    },
    APPLIED_FOR_COURSES{
        @Override
        int order(){
            return 5;
        }
    },
    DID_NOT_APPLY_FOR_COURSES{
        @Override
        int order(){
            return 6;
        }
    },
    GOT_REJECTED{
        @Override
        int order(){
            return 7;
        }
    },
    BLACKLISTED;


    int order(){
        return 0;
    }
}
