package com.oadultradeepfield.smartotter.utility;

import com.oadultradeepfield.smartotter.SmartOtterConstant;

public class CustomIO {
    // Remove leading, trailing, and continuous spaces
    public static String sanitizeInput(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        return input.replaceAll("\\s+", " ").trim();
    }

    // Helper function to print the bot response with horizontal bar and indentation
    public static void printPretty(String input) {
        String formattedInput = SmartOtterConstant.HORIZONTAL_BAR + "\n" +
                input + "\n" +
                SmartOtterConstant.HORIZONTAL_BAR;
        System.out.println(formattedInput.indent(4));
    }
}
