package com.oadultradeepfield.smartotter.comand;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses user input into corresponding {@link Command} instances.
 */
public class CommandParser {
    private final Map<String, CommandFactory> commandFactories = new HashMap<>();

    public CommandParser() {
        registerCommands();
    }

    /**
     * Registers the built-in commands.
     */
    private void registerCommands() {
        commandFactories.put("list", ListCommand::fromInput);
        commandFactories.put("bye", ByeCommand::fromInput);
        commandFactories.put("mark", MarkCommand::fromInput);
        commandFactories.put("unmark", UnmarkCommand::fromInput);
    }

    /**
     * Parses the given input string and returns the corresponding {@link Command}.
     * Defaults to {@link AddCommand} if no matching command is found.
     *
     * @param input the raw user input
     * @return the parsed command
     * @throws IllegalArgumentException if the input is invalid for the matched command
     */
    public Command parse(String input) throws IllegalArgumentException {
        String[] parts = input.split(" ", 2); // Split into maximum 2 parts
        String commandWord = parts[0].toLowerCase();

        // Special case: "list something" or "bye someone" should be treated as add command
        if ((commandWord.equals("list") || commandWord.equals("bye")) && parts.length > 1) {
            return new AddCommand(input);
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

            throw new IllegalArgumentException("Command '" + commandWord + "' requires one argument");
        }

        // Default to AddCommand for unrecognized commands
        return new AddCommand(input);
    }

    /**
     * Functional interface for creating {@link Command} instances from input.
     */
    @FunctionalInterface
    private interface CommandFactory {
        Command create(String input) throws IllegalArgumentException;
    }
}
