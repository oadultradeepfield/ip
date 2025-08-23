package com.oadultradeepfield.smartotter.task;

import com.oadultradeepfield.smartotter.SmartOtterException;

/** Utility class for parsing task strings into {@link Task} objects. */
public class TaskParser {
  /**
   * Parses a line of text into a {@link Task}.
   *
   * <p>Expected format: {@code type | status | name | [deadline/from] | [to]}.
   *
   * @param line the line to parse
   * @return the parsed {@link Task}
   * @throws SmartOtterException if the line is invalid or cannot be parsed
   */
  public static Task parse(String line) throws SmartOtterException {
    // Split by " | "
    String[] parts = line.split("\\s*\\|\\s*");

    if (parts.length < 3) {
      throw new SmartOtterException("Not enough parts");
    }

    String type = parts[0];
    int status;
    try {
      status = Integer.parseInt(parts[1]);
    } catch (NumberFormatException e) {
      throw new SmartOtterException("Invalid status number");
    }

    if (status != 0 && status != 1) {
      throw new SmartOtterException("Invalid status value");
    }

    String taskName = parts[2];
    String deadlineOrFrom = (parts.length > 3) ? parts[3] : null;
    String deadlineOrTo = (parts.length > 4) ? parts[4] : null;

    // Skip if required fields are missing
    if ((parts.length > 3 && deadlineOrFrom == null)
        || (parts.length > 4 && deadlineOrTo == null)) {
      throw new SmartOtterException("Missing deadline fields");
    }

    return createTask(type, status, taskName, deadlineOrFrom, deadlineOrTo);
  }

  /**
   * Creates a {@link Task} object based on type and properties.
   *
   * @param type the task type ("T" = ToDo, "D" = Deadline, "E" = Event)
   * @param status 0 if not done, 1 if done
   * @param taskName the task description
   * @param deadlineOrFrom the deadline (for Deadline) or start time (for Event)
   * @param deadlineOrTo the end time (for Event), may be {@code null}
   * @return the created {@link Task}, or {@code null} if type is unknown
   */
  public static Task createTask(
      String type, int status, String taskName, String deadlineOrFrom, String deadlineOrTo) {
    Task task =
        switch (type) {
          case "T" -> new ToDoTask(taskName);
          case "D" -> new DeadlineTask(taskName, deadlineOrFrom);
          case "E" -> new EventTask(taskName, deadlineOrFrom, deadlineOrTo);
          default -> null;
        };

    if (task != null && status == 1) {
      task.setDone(true);
    }

    return task;
  }
}
