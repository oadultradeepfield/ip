package com.oadultradeepfield.smartotter;

import com.oadultradeepfield.smartotter.command.ByeCommand;
import com.oadultradeepfield.smartotter.command.CommandContext;
import com.oadultradeepfield.smartotter.command.CommandParser;
import com.oadultradeepfield.smartotter.command.Executable;
import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.util.CustomIO;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Main class for the SmartOtter interactive console application. */
public class SmartOtter {
  private static final List<Task> tasks = new ArrayList<>();
  private static final CommandParser parser = new CommandParser();
  private static final CommandContext context = new CommandContext(tasks);
  private static final Scanner scanner = new Scanner(System.in);

  /** Entry point. Runs interactive loop until user exits. */
  public static void main(String[] args) {
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
