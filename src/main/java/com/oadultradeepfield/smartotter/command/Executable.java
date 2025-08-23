package com.oadultradeepfield.smartotter.command;

import com.oadultradeepfield.smartotter.SmartOtterException;

/**
 * Represents a user command that can be executed with a given context.
 */
public interface Executable {
    /**
     * Executes the command using the provided context.
     *
     * @param context the execution context
     */
    void execute(CommandContext context) throws SmartOtterException;
}
