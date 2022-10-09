package com.example.data;

import java.time.OffsetDateTime;

public class Traffic {

  private OffsetDateTime period;
  private int count;

  public Traffic(){
  }

  public Traffic(OffsetDateTime period, int count) {
    this.period = period;
    this.count = count;
  }

  public OffsetDateTime getPeriod() {
    return period;
  }

  public void setPeriod(OffsetDateTime period) {
    this.period = period;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
