package com.oadultradeepfield.smartotter.comand;

import java.util.HashMap;
import java.util.Map;

/** Parses user input into corresponding {@link Executable} instances. */
public class CommandParser {
  private final Map<String, CommandFactory> commandFactories = new HashMap<>();

  public CommandParser() {
    registerCommands();
  }

  /** Registers the built-in commands. */
  private void registerCommands() {
    commandFactories.put("todo", AddTodoCommand::fromInput);
    commandFactories.put("deadline", AddDeadlineTaskCommand::fromInput);
    commandFactories.put("event", AddEventTaskCommand::fromInput);
    commandFactories.put("list", ListCommand::fromInput);
    commandFactories.put("bye", ByeCommand::fromInput);
    commandFactories.put("mark", MarkCommand::fromInput);
    commandFactories.put("unmark", UnmarkCommand::fromInput);
  }

  /**
   * Parses the given input string and returns the corresponding {@link Executable}.
   *
   * @param input the raw user input
   * @return the parsed command
   * @throws IllegalArgumentException if the input is not an available command or the arguments are
   *     invalid for the matched command
   */
  public Executable parse(String input) throws IllegalArgumentException {
    String[] parts = input.split(" ", 2); // Split into maximum 2 parts
    String commandWord = parts[0].toLowerCase();

    // Special case: "list something" or "bye someone" should be treated as add command
    if ((commandWord.equals("list") || commandWord.equals("bye")) && parts.length > 1) {
      return new AddTodoCommand(input);
    }

    CommandFactory factory = commandFactories.get(commandWord);

    if (factory != null) {
      // Handle commands that don't need parameters (bye, list)
      if (commandWord.equals("bye") || commandWord.equals("list")) {
        return factory.create("");
      }

      if (parts.length > 1) {
        return factory.create(parts[1]);
      }

      throw new IllegalArgumentException(
          "Command '%s' requires one argument".formatted(commandWord));
    }

    throw new IllegalArgumentException(
        "Unknown command: '%s'.\nI am not smart enough to understand,\nplease try again."
            .formatted(commandWord));
  }

  /** Functional interface for creating {@link Executable} instances from input. */
  @FunctionalInterface
  private interface CommandFactory {
    Executable create(String input) throws IllegalArgumentException;
  }
}
