package com.example.util;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

  public static final String TIMEZONE = "UTC";

  public static OffsetDateTime toDateTime(String formattedDate) {
    return ZonedDateTime.parse(
        formattedDate,
        DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.of(TIMEZONE))).toOffsetDateTime();
  }

  public static String toFormattedDateTime(OffsetDateTime dateTime) {
    return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }

  public static String toFormattedDate(OffsetDateTime dateTime) {
    return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
  }

}
