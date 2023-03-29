package com.example.neolabs.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public static long findDifference(LocalDateTime time1, LocalDateTime time2, ChronoUnit unit) {
        if (time1.isAfter(time2)) {
            LocalDateTime temp = time1;
            time1 = time2;
            time2 = temp;
        }
        return time1.until(time2, unit);
    }

    public static ZoneId getZoneId(){
        return ZoneId.of("Asia/Bishkek");
    }
}

