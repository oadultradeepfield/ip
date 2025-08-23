package com.oadultradeepfield.smartotter.util;

import com.oadultradeepfield.smartotter.SmartOtterConstant;
import com.oadultradeepfield.smartotter.SmartOtterException;
import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.task.TaskParser;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
  public static List<Task> readTasksFromFile(String fileName) {
    List<Task> tasks = new ArrayList<>();
    int success = 0, failed = 0;

    // If file doesn't exist, return empty list without error
    if (!Files.exists(Paths.get(fileName))) {
      return tasks;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        try {
          Task task = TaskParser.parse(line);
          tasks.add(task);
          success++;
        } catch (SmartOtterException e) {
          failed++;
        }
      }
    } catch (IOException e) {
      CustomIO.printPretty(
          CustomIO.formatError("Error reading file: %s (%s)".formatted(fileName, e.getMessage())));
    }

    CustomIO.printPretty("Parsing complete: %s success, %s failed".formatted(success, failed));
    return tasks;
  }

  public static void saveTasksToFile(String fileName, List<Task> tasks) {
    try {
      Path path = Path.of(fileName);

      // Ensure parent directory exists
      if (path.getParent() != null && Files.notExists(path.getParent())) {
        Files.createDirectories(path.getParent());
      }

      // Open writer (auto-creates file if missing, truncates if exists)
      try (BufferedWriter writer =
          Files.newBufferedWriter(
              path,
              StandardOpenOption.CREATE,
              StandardOpenOption.TRUNCATE_EXISTING,
              StandardOpenOption.WRITE)) {
        for (Task task : tasks) {
          writer.write(task.convertToLine());
          writer.newLine();
        }
        CustomIO.printPretty(
            "Tasks saved to %s successfully before shutdown 🐟"
                .formatted(SmartOtterConstant.SAVE_PATH));
      }

    } catch (IOException e) {
      CustomIO.printPretty(
          CustomIO.formatError("Error saving file: " + fileName + " (" + e.getMessage() + ")"));
    }
  }
}
