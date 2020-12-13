package com.doublebooked.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {

  private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
  private final static SimpleDateFormat FORMATTER = new SimpleDateFormat(DATE_FORMAT);

  @JsonProperty(required = true)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private Date startTime;

  @JsonProperty(required = true)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private Date endTime;

  private Event() {
  }

  private Event(Date startTime, Date endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("(");

    FORMATTER.setTimeZone(TimeZone.getTimeZone("GMT"));
    sb.append(FORMATTER.format(this.startTime)).append(" | ");
    sb.append(FORMATTER.format(this.endTime)).append(")");

    return sb.toString();
  }
}
