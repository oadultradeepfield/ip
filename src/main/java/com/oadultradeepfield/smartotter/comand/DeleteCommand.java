package com.oadultradeepfield.smartotter.comand;

import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.util.CustomIO;
import java.util.List;

/** Deletes the specified task from the list. */
public record DeleteCommand(int taskNumber) implements Executable {
  /**
   * Creates a {@code DeleteCommand} for the given task number.
   *
   * @param taskNumber the 1-based index of the task to delete as done
   */
  public DeleteCommand {}

  /**
   * Parses input to create a {@code DeleteCommand}.
   *
   * @param taskNumber string representing the 1-based task number
   * @return a new {@code DeleteCommand} instance
   * @throws IllegalArgumentException if the input is not a valid integer
   */
  public static Executable fromInput(String taskNumber) throws IllegalArgumentException {
    try {
      int num = Integer.parseInt(taskNumber);
      return new DeleteCommand(num);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid task number: %s".formatted(taskNumber));
    }
  }

  /** {@inheritDoc} */
  @Override
  public void execute(CommandContext context) {
    List<Task> tasks = context.tasks();
    if (taskNumber >= 1 && taskNumber <= tasks.size()) {
      Task task = tasks.get(taskNumber - 1);
      tasks.remove(task);

      String message =
          """
                        Got it! I have deleted the task:
                            %s
                        Now you have %d tasks, namely"""
              .formatted(task, context.tasks().size());
      CustomIO.printPretty(message);
    } else {
      CustomIO.printError("There is no task with number %d".formatted(taskNumber));
    }
  }
}
