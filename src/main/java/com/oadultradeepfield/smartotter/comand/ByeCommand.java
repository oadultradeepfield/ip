package com.oadultradeepfield.smartotter.comand;

import com.oadultradeepfield.smartotter.SmartOtterConstant;
import com.oadultradeepfield.smartotter.utility.CustomIO;

public class ByeCommand implements Command {
    public static Command fromInput(String input) throws IllegalArgumentException {
        return new ByeCommand();
    }

    @Override
    public void execute(CommandContext context) {
        CustomIO.printPretty(SmartOtterConstant.FAREWELL_MESSAGE);
    }
}
