package com.gcloud.tracker.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Oleksandr Storozhuk
 * @version 0.0.1
 * created on 21/08/21
 */
public class SchedulerUtils {
    public static int initialDelayMinutes(int minutesTimeStump) {
        ZonedDateTime time = LocalDateTime.now().atZone(ZoneId.of("Europe/Moscow"));
        int currTimeMin = time.getHour() * 60 + time.getMinute();
        if(currTimeMin < minutesTimeStump) {
            return minutesTimeStump - currTimeMin;
        } else {
            return 1440 - currTimeMin + minutesTimeStump;
        }
    }
}
