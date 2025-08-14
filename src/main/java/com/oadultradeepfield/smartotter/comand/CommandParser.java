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
        String[] parts = input.split(" ");
        String commandWord = parts[0].toLowerCase();
        CommandFactory factory = commandFactories.get(commandWord);

        if (factory != null) {
            return factory.create(input);
        }

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
