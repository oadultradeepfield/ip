package com.oadultradeepfield.smartotter.comand;

import com.oadultradeepfield.smartotter.SmartOtterException;

/** Parses user input into corresponding {@link Executable} instances. */
public class CommandParser {
  /**
   * * Parses the given input string and returns the corresponding {@link Executable}.
   *
   * @param input the raw user input
   * @return the parsed command
   * @throws SmartOtterException if the input is not an available command or the arguments are
   *     invalid for the matched command
   */
  public Executable parse(String input) throws SmartOtterException {
    String[] parts = input.split(" ", 2); // Split into maximum 2 parts;
    String commandWord = parts[0].toLowerCase();

    CommandType type = CommandType.fromKeyword(commandWord);

    // Special case: treat "list something" or "bye someone" as AddTodoCommand
    if ((commandWord.equals("list") || commandWord.equals("bye")) && parts.length > 1) {
      return new AddTodoCommand(input);
    }

    if (type != null) {
      if (commandWord.equals("bye") || commandWord.equals("list")) {
        return type.create("");
      }
      if (parts.length > 1) {
        return type.create(parts[1]);
      }
      throw new SmartOtterException("Command '%s' requires one argument".formatted(commandWord));
    }

    throw new SmartOtterException(
        "Unknown command: '%s'.\nI am not smart enough to understand,\nplease try again."
            .formatted(commandWord));
  }
}
