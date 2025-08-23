package com.oadultradeepfield.smartotter.task;

/** A {@link Task} representing an event with a start and end date. */
public class EventTask extends Task {
  private final String dateFrom;
  private final String dateTo;

  /**
   * Creates an event task with the given name, start date, and end date.
   *
   * @param taskName the name of the event
   * @param dateFrom the start date of the event
   * @param dateTo the end date of the event
   */
  public EventTask(String taskName, String dateFrom, String dateTo) {
    super(taskName);
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
  }

  /** {@inheritDoc} */
  @Override
  public String getIcon() {
    return "[E] %s".formatted(super.getIcon());
  }

  /** Returns the string representation of this event, including its date range. */
  @Override
  public String toString() {
    return "%s (from: %s, to: %s)".formatted(super.toString(), dateFrom, dateTo);
  }

  /** {@inheritDoc} */
  @Override
  public String convertToLine() {
    return "E | %s | %s | %s".formatted(super.convertToLine(), dateFrom, dateTo);
  }
}
