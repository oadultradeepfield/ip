package com.oadultradeepfield.smartotter.task;

public class ToDoTask extends Task {
  public ToDoTask(String taskName) {
    super(taskName);
  }

  @Override
  public String getIcon() {
    return "[T] " + super.getIcon();
  }
}
