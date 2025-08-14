package com.oadultradeepfield.smartotter;

import com.oadultradeepfield.smartotter.utility.CustomIO;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main class for the SmartOtter interactive console application.
 */
public class SmartOtter {
    private static void greeting() {
        CustomIO.printPretty(SmartOtterConstant.GREETING_MESSAGE_TEMPLATE);
    }

    /**
     * Processes user input and generates appropriate response.
     * Expects input to be pre-sanitized.
     *
     * @param input sanitized user input
     */
    private static void respondToUser(String input) {
        if (isExitCommand(input)) {
            CustomIO.printPretty(SmartOtterConstant.FAREWELL_MESSAGE);
        } else {
            CustomIO.printPretty(input);
        }
    }

    /**
     * Checks if input is an exit command.
     * Expects input to be pre-sanitized.
     *
     * @param input sanitized user input
     * @return true if input matches exit command
     */
    private static boolean isExitCommand(String input) {
        return SmartOtterConstant.BYE_COMMAND.equalsIgnoreCase(input);
    }

    /**
     * Entry point. Runs interactive loop until user exits.
     */
    public static void main(String[] args) {
        greeting();

        Scanner scanner = new Scanner(System.in);
        String input = "";

        do {
            try {
                input = CustomIO.sanitizeInput(scanner.nextLine());
                respondToUser(input);
            } catch (NoSuchElementException | IllegalArgumentException e) {
                CustomIO.printError(e.getMessage());
            }
        } while (!isExitCommand(input));
    }
}