package com.example.neolabs.util;

import com.example.neolabs.enums.ApplicationStatus;

public class StatusUtil {
    public static ApplicationStatus getApplicationStatus(int n){
        if (n == 1) {
            return ApplicationStatus.WAITING_FOR_CALL;
        } else if (n == 2) {
            return ApplicationStatus.CALL_RECEIVED;
        } else if (n == 3) {
            return ApplicationStatus.APPLIED_FOR_TRIAL;
        } else if (n == 4) {
            return ApplicationStatus.ATTENDED_TRIAL;
        } else if (n == 5) {
            return ApplicationStatus.APPLIED_FOR_COURSES;
        } else if (n == 6) {
            return ApplicationStatus.DID_NOT_APPLY_FOR_COURSES;
        } else if (n == 7) {
            return ApplicationStatus.GOT_REJECTED;
        } else {
            return ApplicationStatus.BLACKLISTED;
        }
    }
}
