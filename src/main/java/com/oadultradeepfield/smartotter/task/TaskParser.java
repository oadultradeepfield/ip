package com.oadultradeepfield.smartotter.task;

import com.oadultradeepfield.smartotter.SmartOtterException;

public class TaskParser {
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
