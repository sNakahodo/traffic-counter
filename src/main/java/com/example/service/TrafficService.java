package com.example.service;

import com.example.data.Traffic;
import com.example.util.MapUtil;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrafficService {

  private List<Traffic> trafficInput;

  public TrafficService(List<Traffic> trafficInput) {
    this.trafficInput = trafficInput;
  }

  public int getTotalCount() {
    return getTotalCount(trafficInput);
  }

  public Map<OffsetDateTime, Integer> getTrafficByDate() {
    Map<OffsetDateTime, Integer> trafficByDate = MapUtil.createWithDateComparator();
    trafficInput.stream().forEach(traffic -> {
      OffsetDateTime period = traffic.getPeriod();
      OffsetDateTime date = OffsetDateTime.of(
          period.getYear(),
          period.getMonthValue(),
          period.getDayOfMonth(),
          0, 0, 0, 0, ZoneOffset.UTC);
      int count;

      if (trafficByDate.containsKey(date)) {
        count = trafficByDate.get(date) + traffic.getCount();
      } else {
        count = traffic.getCount();
      }

      trafficByDate.put(date, count);
    });

    return trafficByDate;
  }

  public Map<OffsetDateTime, Integer> getBiggestTraffic(int limit) {
    List<Traffic> mutableTrafficInput = new ArrayList<>(trafficInput);

    mutableTrafficInput.sort((traffic1, traffic2)
        -> traffic2.getCount() - traffic1.getCount());

    return MapUtil.mapFromList(mutableTrafficInput.subList(0, limit));
  }

  public Map<OffsetDateTime, Integer> getSmallestTrafficPeriodChunk(int chunk) {
    int chunkIndex = 0;
    int smallestChunkCount = 0;
    Map<OffsetDateTime, Integer> nominatedTrafficChunk = new HashMap<>();

    while (chunkIndex + chunk <= trafficInput.size()) {
      List<Traffic> trafficChunk = trafficInput.subList(chunkIndex, chunkIndex + chunk);
      int chunkTotal = getTotalCount(trafficChunk);

      if (smallestChunkCount == 0 || chunkTotal < smallestChunkCount) {
        smallestChunkCount = chunkTotal;
        nominatedTrafficChunk = MapUtil.mapFromList(trafficChunk);
      }

      chunkIndex++;
    }

    return nominatedTrafficChunk;
  }

  private int getTotalCount(List<Traffic> trafficList) {
    int total = 0;
    for (Traffic traffic : trafficList) {
      total += traffic.getCount();
    }
    return total;
  }

}
