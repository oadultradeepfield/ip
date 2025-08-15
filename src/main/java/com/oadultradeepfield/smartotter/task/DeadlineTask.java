package com.oadultradeepfield.smartotter.task;

public class DeadlineTask extends Task {
  private final String deadline;

  public DeadlineTask(String taskName, String deadline) {
    super(taskName);
    this.deadline = deadline;
  }

  @Override
  public String getIcon() {
    return "[D] " + super.getIcon();
  }

  @Override
  public String toString() {
    return super.toString() + "(by: " + deadline + ")";
  }
}
