package com.oadultradeepfield.smartotter.task;

/** A {@link Task} with a deadline. */
public class DeadlineTask extends Task {
  private final String deadline;

  /**
   * Creates a deadline task with the given name and deadline.
   *
   * @param taskName the name of the task
   * @param deadline the deadline description or date
   */
  public DeadlineTask(String taskName, String deadline) {
    super(taskName);
    this.deadline = deadline;
  }

  /** {@inheritDoc} */
  @Override
  public String getIcon() {
    return "[D] %s".formatted(super.getIcon());
  }

  /** Returns the string representation of this task including its deadline. */
  @Override
  public String toString() {
    return "%s (by: %s)".formatted(super.toString(), deadline);
  }
}
