package com.oadultradeepfield.smartotter.comand;

import com.oadultradeepfield.smartotter.SmartOtterConstant;
import com.oadultradeepfield.smartotter.utility.CustomIO;

/** A command that displays the farewell message and ends the session. */
public class ByeCommand implements Command {
  /**
   * Creates a {@code ByeCommand} from user input. This command ignores any provided input.
   *
   * @param input unused input string
   * @return a new {@code ByeCommand} instance
   * @throws IllegalArgumentException never thrown in this implementation
   */
  @SuppressWarnings("unused")
  public static Command fromInput(String input) throws IllegalArgumentException {
    return new ByeCommand();
  }

  /** {@inheritDoc} */
  @Override
  public void execute(CommandContext context) {
    CustomIO.printPretty(SmartOtterConstant.FAREWELL_MESSAGE);
  }
}
