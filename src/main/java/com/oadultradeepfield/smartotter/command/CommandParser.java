package com.oadultradeepfield.smartotter.command;

import java.util.Set;

import com.oadultradeepfield.smartotter.SmartOtterException;
import com.oadultradeepfield.smartotter.util.CustomIO;

/**
 * Parses user input into corresponding {@link Executable} instances.
 */
public class CommandParser {
    /**
     * * Parses the given input string and returns the corresponding {@link Executable}.
     *
     * @param input the raw user input
     * @return the parsed command
     * @throws SmartOtterException if the input is not an available command or the arguments are
     *                             invalid for the matched command
     */
    public Executable parse(String input) throws SmartOtterException {
        assert input.equals(CustomIO.sanitizeInput(input)) : "Input should be sanitized before parsing";

        String[] parts = input.split(" ", 2); // Split into maximum 2 parts;
        String commandWord = parts[0].toLowerCase();

        CommandType type = CommandType.fromKeyword(commandWord);

        if (type == null) {
            throw new SmartOtterException(
                "Unknown command: '%s'.\nI am not smart enough to understand,\nplease try again."
                    .formatted(commandWord));
        }

        // Commands that do not take arguments
        Set<String> noArgCommands = Set.of("bye", "list", "today");
        if (noArgCommands.contains(commandWord)) {
            if (parts.length > 1) {
                throw new SmartOtterException(
                    "Command '%s' does not take any arguments".formatted(commandWord));
            }
            return type.create("");
        }

        // Commands that require arguments
        if (parts.length < 2) {
            throw new SmartOtterException("Command '%s' requires one argument".formatted(commandWord));
        }

        return type.create(parts[1]);
    }
}
