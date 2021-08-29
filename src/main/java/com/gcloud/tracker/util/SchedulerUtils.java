package com.gcloud.tracker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Oleksandr Storozhuk
 * @version 0.0.1
 * created on 21/08/21
 */
public class SchedulerUtils {
    private final static Logger log = LoggerFactory.getLogger(SchedulerUtils.class);

    /**
     * Calculates the time to the required time label in minutes.
     *
     * @param minutesTimeStump time label in minutes
     * @return time in minutes left for the needed time label.
     */
    public static int initialDelayMinutes(int minutesTimeStump) {
        Instant nowUtc = Instant.now();
        ZoneId euMSK = ZoneId.of("Europe/Moscow");
        ZonedDateTime nowEuMSK = ZonedDateTime.ofInstant(nowUtc, euMSK);

        int currTimeMin = nowEuMSK.getHour() * 60 + nowEuMSK.getMinute();
        if (currTimeMin < minutesTimeStump) {
            return minutesTimeStump - currTimeMin;
        } else {
            return 1440 - currTimeMin + minutesTimeStump;
        }
    }
}
