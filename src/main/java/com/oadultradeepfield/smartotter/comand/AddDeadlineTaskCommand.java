package com.oadultradeepfield.smartotter.comand;

import com.oadultradeepfield.smartotter.task.DeadlineTask;

/** A command that adds a new deadline task to the task list. */
public final class AddDeadlineTaskCommand extends AddTaskCommand {
  /**
   * Creates an {@code AddDeadlineTaskCommand} with the specified task.
   *
   * @param taskName the task name
   * @param deadline the deadline
   */
  public AddDeadlineTaskCommand(String taskName, String deadline) {
    super(new DeadlineTask(taskName, deadline));
  }

  /**
   * Parses input to create an {@code AddDeadlineTaskCommand}.
   *
   * <p>Example: {@code deadline return book /by Sunday}
   *
   * @param input the full user input string without the command word
   * @return a new {@code AddDeadlineTaskCommand} instance
   * @throws IllegalArgumentException if the input format is invalid
   */
  public static Executable fromInput(String input) throws IllegalArgumentException {
    String[] parts = input.split("/by", 2);
    if (parts.length < 2) {
      throw new IllegalArgumentException(
          "Incorrect deadline format! Use: deadline <desc> /by <time>");
    }
    String taskName = parts[0].trim();
    String deadline = parts[1].trim();
    if (taskName.isEmpty() || deadline.isEmpty()) {
      throw new IllegalArgumentException("Task name and deadline cannot be empty.");
    }
    return new AddDeadlineTaskCommand(taskName, deadline);
  }
}
