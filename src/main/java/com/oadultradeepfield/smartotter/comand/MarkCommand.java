package com.oadultradeepfield.smartotter.comand;

import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.util.CustomIO;
import java.util.List;

/** Marks a specified task as done. */
public record MarkCommand(int taskNumber) implements Command {
  /**
   * Creates a {@code MarkCommand} for the given task number.
   *
   * @param taskNumber the 1-based index of the task to mark as done
   */
  public MarkCommand {}

  /**
   * Parses input to create a {@code MarkCommand}.
   *
   * @param taskNumber string representing the 1-based task number
   * @return a new {@code MarkCommand} instance
   * @throws IllegalArgumentException if the input is not a valid integer
   */
  public static Command fromInput(String taskNumber) throws IllegalArgumentException {
    try {
      int num = Integer.parseInt(taskNumber);
      return new MarkCommand(num);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid task number: " + taskNumber);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void execute(CommandContext context) {
    List<Task> tasks = context.tasks();
    if (taskNumber >= 1 && taskNumber <= tasks.size()) {
      Task task = tasks.get(taskNumber - 1);
      task.setDone(true);
      CustomIO.printPretty("Great work! I have marked the task as done!\n" + task);
    } else {
      CustomIO.printError("There is no task with number " + taskNumber);
    }
  }
}
