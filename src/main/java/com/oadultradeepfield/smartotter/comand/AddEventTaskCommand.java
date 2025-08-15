package com.oadultradeepfield.smartotter.comand;

import com.oadultradeepfield.smartotter.SmartOtterException;
import com.oadultradeepfield.smartotter.task.EventTask;

/** A command that adds a new event task to the task list. */
public final class AddEventTaskCommand extends AddTaskCommand {
  /**
   * Creates an {@code AddEventTaskCommand} with the specified task.
   *
   * @param taskName the event task name
   * @param from the start time
   * @param to the end time
   */
  public AddEventTaskCommand(String taskName, String from, String to) {
    super(new EventTask(taskName, from, to));
  }

  /**
   * Parses input to create an {@code AddEventTaskCommand}.
   *
   * <p>Example: {@code event project meeting /from Mon 2pm /to 4pm}
   *
   * @param input the full user input string without the command word
   * @return a new {@code AddEventTaskCommand} instance
   * @throws SmartOtterException if the input format is invalid
   */
  public static Executable fromInput(String input) throws SmartOtterException {
    String[] fromSplit = input.split("/from", 2);
    if (fromSplit.length < 2) {
      throw new SmartOtterException(
          "Incorrect event format! Use: event <desc> /from <start> /to <end>");
    }
    String taskName = fromSplit[0].trim();

    String[] toSplit = fromSplit[1].split("/to", 2);
    if (toSplit.length < 2) {
      throw new SmartOtterException(
          "Incorrect event format! Use: event <desc> /from <start> /to <end>");
    }
    String from = toSplit[0].trim();
    String to = toSplit[1].trim();

    if (taskName.isEmpty() || from.isEmpty() || to.isEmpty()) {
      throw new SmartOtterException("Task name, start, and end time cannot be empty.");
    }

    return new AddEventTaskCommand(taskName, from, to);
  }
}
