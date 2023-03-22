package com.example.neolabs.enums;

public enum Content {

    DEPARTMENT {
        @Override
        public String toString() {
            return "Department";
        }
    },
    Course {
        @Override
        public String toString() {
            return "Course";
        }
    }
}

