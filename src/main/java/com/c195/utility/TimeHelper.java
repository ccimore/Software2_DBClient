package com.c195.utility;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This abstract class assists with Time Date manipulation.
 */
public abstract class TimeHelper {

    /**
     * Converts Local date time to UTC.
     *
     * @param dateTime Local date time
     * @return UTC date time
     */
    public static LocalDateTime convertToUTC(LocalDateTime dateTime) {

        ZonedDateTime zoneDateTime = dateTime.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime zoneUTCDateTime = zoneDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime UTCDateTime = zoneUTCDateTime.toLocalDateTime();
        return UTCDateTime;

    }

    /**
     * Converts UTC to local date Time.
     *
     * @param UTCDateTime UTC date time
     * @return Local date time
     */
    public static LocalDateTime convertUTCToLocal(LocalDateTime UTCDateTime){
        ZonedDateTime UTCZoneDateTime = UTCDateTime.atZone(ZoneId.of("UTC"));
        ZonedDateTime localZoneDateTime = UTCZoneDateTime.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime localDateTime = localZoneDateTime.toLocalDateTime();
        return localDateTime;
    }

    /**
     * Converts local date time to eastern date time.
     *
     * @param localDateTime Local date time
     * @return Eastern date time
     */
    public static LocalDateTime convertLocalToEst(LocalDateTime localDateTime){
        ZonedDateTime localZoneDateTime = localDateTime.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime estZoneDateTime = localZoneDateTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalDateTime estDateTime = estZoneDateTime.toLocalDateTime();
        return estDateTime;
    }
}
