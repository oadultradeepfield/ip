package com.oadultradeepfield.smartotter;

import com.oadultradeepfield.smartotter.command.ByeCommand;
import com.oadultradeepfield.smartotter.command.CommandContext;
import com.oadultradeepfield.smartotter.command.CommandParser;
import com.oadultradeepfield.smartotter.command.Executable;
import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.util.CustomIO;
import com.oadultradeepfield.smartotter.util.FileManager;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Main class for the SmartOtter interactive console application. */
public class SmartOtter {
  private static final List<Task> tasks =
      FileManager.readTasksFromFile(SmartOtterConstant.SAVE_PATH);
  private static final CommandParser parser = new CommandParser();
  private static final CommandContext context = new CommandContext(tasks);
  private static final Scanner scanner = new Scanner(System.in);

  /** Entry point. Runs interactive loop until user exits. */
  public static void main(String[] args) {
    // Add shutdown hook to save tasks no matter what
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  FileManager.saveTasksToFile(SmartOtterConstant.SAVE_PATH, tasks);
                  CustomIO.printPretty(
                      "Tasks saved to %s successfully before shutdown 🐟"
                          .formatted(SmartOtterConstant.SAVE_PATH));
                }));

    CustomIO.printPretty(SmartOtterConstant.GREETING_MESSAGE_TEMPLATE);

    String input;
    while (true) {
      try {
        input = CustomIO.sanitizeInput(scanner.nextLine());
        Executable executable = parser.parse(input);
        executable.execute(context);

        if (executable instanceof ByeCommand) {
          break;
        }
      } catch (SmartOtterException e) {
        CustomIO.printPretty(e.getMessage());
      } catch (NoSuchElementException | IllegalStateException | IllegalArgumentException e) {
        CustomIO.printPretty(CustomIO.formatError(e.getMessage()));
      }
    }
  }
}
