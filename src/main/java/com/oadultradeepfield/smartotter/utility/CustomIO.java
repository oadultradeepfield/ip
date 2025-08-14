package com.oadultradeepfield.smartotter.utility;

import com.oadultradeepfield.smartotter.SmartOtterConstant;

/** Utility class for input/output operations in SmartOtter. */
public class CustomIO {
  /**
   * Normalizes whitespace by removing leading/trailing spaces and collapsing multiple spaces into
   * single spaces.
   *
   * @param input the string to sanitize
   * @return sanitized string, or empty string if input is null/empty
   */
  public static String sanitizeInput(String input) {
    if (input == null || input.isEmpty()) {
      return "";
    }
    return input.replaceAll("\\s+", " ").trim();
  }

  /**
   * Prints text with horizontal bars and indentation for bot responses.
   *
   * @param input the message to display
   */
  public static void printPretty(String input) {
    String formattedInput =
        SmartOtterConstant.HORIZONTAL_BAR + "\n" + input + "\n" + SmartOtterConstant.HORIZONTAL_BAR;
    System.out.println(formattedInput.indent(4));
  }

  /**
   * Prints error messages using the pretty format with error prefix.
   *
   * @param input the error message to display
   */
  public static void printError(String input) {
    printPretty("⛔️ Error - " + input);
  }
}
