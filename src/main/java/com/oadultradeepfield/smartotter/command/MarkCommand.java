package com.oadultradeepfield.smartotter.command;

import com.oadultradeepfield.smartotter.SmartOtterException;
import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.util.CustomIO;
import java.util.Optional;

/** Marks a specified task as done. */
public record MarkCommand(int taskNumber) implements Executable {
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
   * @throws SmartOtterException if the input is not a valid integer
   */
  public static Executable fromInput(String taskNumber) throws SmartOtterException {
    try {
      int num = Integer.parseInt(taskNumber);
      return new MarkCommand(num);
    } catch (NumberFormatException e) {
      throw new SmartOtterException("Invalid task number: %s".formatted(taskNumber));
    }
  }

  /** {@inheritDoc} */
  @Override
  public void execute(CommandContext context) {
    // Convert 1-based to 0-based index and use getTask()
    Optional<Task> taskToMark = context.getTask(taskNumber - 1);

    if (taskToMark.isPresent()) {
      Task task = taskToMark.get();
      task.setDone(true);
      CustomIO.printPretty("Great work! I have marked the task as done!\n%s".formatted(task));
    } else {
      CustomIO.printPretty(
          CustomIO.formatError("There is no task with number %d".formatted(taskNumber)));
    }
  }
}
