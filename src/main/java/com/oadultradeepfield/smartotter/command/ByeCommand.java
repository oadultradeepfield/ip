package com.oadultradeepfield.smartotter.command;

import com.oadultradeepfield.smartotter.SmartOtterConstant;
import com.oadultradeepfield.smartotter.util.CustomIO;

/** A command that displays the farewell message and ends the session. */
public class ByeCommand implements Executable {
  /**
   * Creates a {@code ByeCommand} from user input. This command ignores any provided input.
   *
   * @param input unused input string
   * @return a new {@code ByeCommand} instance
   */
  @SuppressWarnings("unused")
  public static Executable fromInput(String input) {
    return new ByeCommand();
  }

  /** {@inheritDoc} */
  @Override
  public void execute(CommandContext context) {
    CustomIO.printPretty(SmartOtterConstant.FAREWELL_MESSAGE);
  }
}
