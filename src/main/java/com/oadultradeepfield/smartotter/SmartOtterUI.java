package com.oadultradeepfield.smartotter;

import com.oadultradeepfield.smartotter.util.CustomIO;
import java.util.Scanner;

/**
 * Handles the user interface for SmartOtter, including displaying messages and reading user input.
 */
public class SmartOtterUI {
  private final Scanner scanner;

  /**
   * Constructs a SmartOtterUI with the given scanner.
   *
   * @param scanner the Scanner to read user input
   */
  public SmartOtterUI(Scanner scanner) {
    this.scanner = new Scanner(System.in);
  }

  /** Displays the greeting message to the user. */
  public void showGreeting() {
    System.out.println(SmartOtterConstant.GREETING_MESSAGE_TEMPLATE);
  }

  /** Displays a loading error message when tasks cannot be loaded. */
  public void showLoadingError() {
    System.out.println("Could not load tasks. Starting with an empty list.");
  }

  /**
   * Displays a custom message to the user.
   *
   * @param message the message to display
   */
  public void showMessage(String message) {
    CustomIO.printPretty(message);
  }

  /**
   * Reads a line of input from the user.
   *
   * @return the trimmed user input
   */
  public String readCommand() {
    return scanner.nextLine().trim();
  }

  /**
   * Displays an error message to the user.
   *
   * @param error the error message
   */
  public void showError(String error) {
    CustomIO.printPretty(CustomIO.formatError(error));
  }
}
