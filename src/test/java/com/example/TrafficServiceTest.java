package com.example;

import com.example.data.Traffic;
import com.example.service.TrafficService;
import com.example.util.DateUtil;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TrafficServiceTest {

  private static TrafficService trafficService;

  @BeforeAll
  static void setUp() {
    trafficService = new TrafficService(List.of(
        new Traffic(DateUtil.toDateTime("2021-12-01T00:00:00"), 10),
        new Traffic(DateUtil.toDateTime("2021-12-01T00:30:00"), 3),
        new Traffic(DateUtil.toDateTime("2021-12-01T01:00:00"), 2),
        new Traffic(DateUtil.toDateTime("2021-12-01T01:30:00"), 1),
        new Traffic(DateUtil.toDateTime("2021-12-02T00:00:00"), 20),
        new Traffic(DateUtil.toDateTime("2021-12-02T00:30:00"), 15)
    ));
  }

  @Test
  void shouldGetTotalCount() {
    int totalCount = trafficService.getTotalCount();
    Assertions.assertEquals(51, totalCount);
  }

  @Test
  void shouldGetCountByDate() {
    Map<OffsetDateTime, Integer> result = trafficService.getTrafficByDate();
    Assertions.assertEquals(2, result.size());
    Assertions.assertEquals(16, result.get(DateUtil.toDateTime("2021-12-01T00:00:00")));
    Assertions.assertEquals(35, result.get(DateUtil.toDateTime("2021-12-02T00:00:00")));
  }

  @Test
  void shouldTop3GetBiggestTraffic() {
    int limit = 3;
    Map<OffsetDateTime, Integer> result = trafficService.getBiggestTraffic(limit);
    Assertions.assertEquals(limit, result.size());
    Assertions.assertEquals(20, result.get(DateUtil.toDateTime("2021-12-02T00:00:00")));
    Assertions.assertEquals(15, result.get(DateUtil.toDateTime("2021-12-02T00:30:00")));
    Assertions.assertEquals(10, result.get(DateUtil.toDateTime("2021-12-01T00:00:00")));
  }

  @Test
  void shouldThrowOnOutOfBoundsToGetBiggestTraffic() {
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> trafficService.getBiggestTraffic(7));
  }

  @Test
  void shouldGetSmallestTrafficPeriodChunk() {
    int chunk = 3;
    Map<OffsetDateTime, Integer> result = trafficService.getSmallestTrafficPeriodChunk(chunk);
    Assertions.assertEquals(chunk, result.size());
    Assertions.assertEquals(3, result.get(DateUtil.toDateTime("2021-12-01T00:30:00")));
    Assertions.assertEquals(2, result.get(DateUtil.toDateTime("2021-12-01T01:00:00")));
    Assertions.assertEquals(1, result.get(DateUtil.toDateTime("2021-12-01T01:30:00")));
  }

}
