package com.example.util;

import com.example.data.Traffic;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapUtil {

  public static Map<OffsetDateTime, Integer> createWithDateComparator() {
    return new TreeMap<OffsetDateTime, Integer>(new Comparator<OffsetDateTime>() {
      public int compare(OffsetDateTime date1, OffsetDateTime date2) {
        return date1.compareTo(date2);
      }
    });
  }

  public static Map<OffsetDateTime, Integer> mapFromList(List<Traffic> trafficList) {
    Map<OffsetDateTime, Integer> trafficMap = MapUtil.createWithDateComparator();
    trafficList.forEach(t -> trafficMap.put(t.getPeriod(), t.getCount()));

    return trafficMap;
  }

}
