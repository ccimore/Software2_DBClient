package com.c195.utility;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeHelper {

    public static LocalDateTime convertToUTC(LocalDateTime dateTime) {

        ZonedDateTime zoneDateTime = dateTime.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime zoneUTCDateTime = zoneDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime UTCDateTime = zoneUTCDateTime.toLocalDateTime();
        return UTCDateTime;


/*
        Timestamp currentTimeStamp = Timestamp.valueOf(String.valueOf(dateTime));
        LocalDateTime LDT = currentTimeStamp.toLocalDateTime();
        ZonedDateTime ZDT = LDT.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime UTCDateTime = ZDT.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime LDTOut = UTCDateTime.toLocalDateTime();
        String UTCOut = LDTOut.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return UTCOut;

 */
    }

    public static LocalDateTime convertUTCToLocal(LocalDateTime UTCDateTime){
        ZonedDateTime UTCZoneDateTime = UTCDateTime.atZone(ZoneId.of("UTC"));
        ZonedDateTime localZoneDateTime = UTCZoneDateTime.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime localDateTime = localZoneDateTime.toLocalDateTime();
        return localDateTime;
    }

    public static LocalDateTime convertLocalToEst(LocalDateTime localDateTime){
        ZonedDateTime localZoneDateTime = localDateTime.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime estZoneDateTime = localZoneDateTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalDateTime estDateTime = estZoneDateTime.toLocalDateTime();
        return estDateTime;
    }
}
