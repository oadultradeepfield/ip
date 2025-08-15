package com.oadultradeepfield.smartotter.command;

import com.oadultradeepfield.smartotter.SmartOtterException;
import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.util.CustomIO;
import java.util.List;

/** Marks a specified task as not done. */
public record UnmarkCommand(int taskNumber) implements Executable {
  /**
   * Creates an {@code UnmarkCommand} for the given task number.
   *
   * @param taskNumber the 1-based index of the task to unmark
   */
  public UnmarkCommand {}

  /**
   * Parses input to create an {@code UnmarkCommand}.
   *
   * @param input string representing the 1-based task number
   * @return a new {@code UnmarkCommand} instance
   * @throws SmartOtterException the input is not a valid integer
   */
  public static Executable fromInput(String input) throws SmartOtterException {
    try {
      int num = Integer.parseInt(input);
      return new UnmarkCommand(num);
    } catch (NumberFormatException e) {
      throw new SmartOtterException("Invalid task number: %s".formatted(input));
    }
  }

  /** {@inheritDoc} */
  @Override
  public void execute(CommandContext context) {
    List<Task> tasks = context.tasks();
    if (taskNumber >= 1 && taskNumber <= tasks.size()) {
      Task task = tasks.get(taskNumber - 1);
      task.setDone(false);
      CustomIO.printPretty("Got it! I have marked the task as not done\n%s".formatted(task));
    } else {
      CustomIO.printPretty(
          CustomIO.formatError("There is no task with number %d".formatted(taskNumber)));
    }
  }
}
