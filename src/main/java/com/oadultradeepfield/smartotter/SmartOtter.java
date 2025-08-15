package com.oadultradeepfield.smartotter;

import com.oadultradeepfield.smartotter.comand.ByeCommand;
import com.oadultradeepfield.smartotter.comand.CommandContext;
import com.oadultradeepfield.smartotter.comand.CommandParser;
import com.oadultradeepfield.smartotter.comand.Executable;
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

  private static void greeting() {
    CustomIO.printPretty(SmartOtterConstant.GREETING_MESSAGE_TEMPLATE);
  }

  /** Entry point. Runs interactive loop until user exits. */
  public static void main(String[] args) {
    greeting();

    String input;
    while (true) {
      try {
        input = CustomIO.sanitizeInput(scanner.nextLine());
        Executable executable = parser.parse(input);
        executable.execute(context);

        if (executable instanceof ByeCommand) {
          break;
        }
      } catch (NoSuchElementException | IllegalStateException | IllegalArgumentException e) {
        CustomIO.printError(e.getMessage());
      }
    }
  }
}
