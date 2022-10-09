package com.example.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TrafficDataLoader {

  private static final String DATA_FILE_PATH = "traffic-report.txt";
  private static final String DATETIME_ZONE = "UTC";
  private static final int INDEX_DATE = 0;
  private static final int INDEX_COUNT = 1;

  public static List<Traffic> load() {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(
          TrafficDataLoader.class.getClassLoader().getResource(DATA_FILE_PATH).getPath()));
      return reader.lines().map(l -> {
        String[] data = l.split("\s");
        return new Traffic(
            ZonedDateTime.parse(data[INDEX_DATE],
              DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.of(DATETIME_ZONE)))
                .toOffsetDateTime(), Integer.parseInt(data[INDEX_COUNT]));
      }).toList();
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

}
