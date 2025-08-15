package com.oadultradeepfield.smartotter.comand;

/** Represents a user command that can be executed with a given context. */
public interface Executable {
  /**
   * Executes the command using the provided context.
   *
   * @param context the execution context
   */
  void execute(CommandContext context);
}
