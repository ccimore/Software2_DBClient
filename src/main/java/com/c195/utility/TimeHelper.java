package com.c195.utility;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeHelper {

    public static String convertToUTC(String dateTime) {
        Timestamp currentTimeStamp = Timestamp.valueOf(String.valueOf(dateTime));
        LocalDateTime LDT = currentTimeStamp.toLocalDateTime();
        ZonedDateTime ZDT = LDT.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime UTCDateTime = ZDT.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime LDTOut = UTCDateTime.toLocalDateTime();
        String UTCOut = LDTOut.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return UTCOut;
    }
}
