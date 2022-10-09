package com.example;

import com.example.data.TrafficDataLoader;
import com.example.service.TrafficService;
import com.example.util.DateUtil;

public class Main {

  public static void main(String[] args) {
    TrafficService service = new TrafficService(TrafficDataLoader.load());

    System.out.println("========= Number of cars in total =========");
    System.out.println(service.getTotalCount());
    System.out.println("");
    System.out.println("========= Number of cars by date =========");
    service.getTrafficByDate().forEach((date, count) -> {
      System.out.println(DateUtil.toFormattedDate(date) + " " + count);
    });
    System.out.println("");
    System.out.println("========= Top 3 hours with most cars =========");
    service.getBiggestTraffic(3).forEach((date, count) -> {
      System.out.println(DateUtil.toFormattedDateTime(date) + " " + count);
    });
    System.out.println("");
    System.out.println("========= 1.5 hours with least cars =========");
    service.getSmallestTrafficPeriodChunk(3).forEach((date, count) -> {
      System.out.println(DateUtil.toFormattedDateTime(date) + " " + count);
    });
    System.out.println("");

  }
}