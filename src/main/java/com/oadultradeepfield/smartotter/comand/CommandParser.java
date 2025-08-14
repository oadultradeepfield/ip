package com.oadultradeepfield.smartotter.comand;

import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    private final Map<String, CommandFactory> commandFactories = new HashMap<>();

    public CommandParser() {
        registerCommands();
    }

    private void registerCommands() {
        commandFactories.put("list", ListCommand::fromInput);
        commandFactories.put("bye", ByeCommand::fromInput);
        commandFactories.put("mark", MarkCommand::fromInput);
        commandFactories.put("unmark", UnmarkCommand::fromInput);
    }

    public Command parse(String input) throws IllegalArgumentException {
        String[] parts = input.split(" ");
        String commandWord = parts[0].toLowerCase();
        CommandFactory factory = commandFactories.get(commandWord);

        if (factory != null) {
            return factory.create(input);
        }

        return new AddCommand(input);
    }

    @FunctionalInterface
    private interface CommandFactory {
        Command create(String input) throws IllegalArgumentException;
    }
}
