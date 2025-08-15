package com.oadultradeepfield.smartotter.task;

public class EventTask extends Task {
  private final String dateFrom;
  private final String dateTo;

  public EventTask(String taskName, String dateFrom, String dateTo) {
    super(taskName);
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
  }

  @Override
  public String getIcon() {
    return "[E] " + super.getIcon();
  }

  @Override
  public String toString() {
    return super.toString() + "(from: " + this.dateFrom + ", to: " + this.dateTo + ")";
  }
}
