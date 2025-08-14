package com.oadultradeepfield.smartotter;

import com.oadultradeepfield.smartotter.utility.CustomIO;

import java.util.Scanner;

public class SmartOtter {
    private static void greeting() {
        CustomIO.printPretty(SmartOtterConstant.GREETING_MESSAGE_TEMPLATE);
    }

    // Respond to the user, assuming the input has been sanitized before
    private static void respondToUser(String input) {
        if (isExitCommand(input)) {
            CustomIO.printPretty(SmartOtterConstant.FAREWELL_MESSAGE);
        } else {
            CustomIO.printPretty(input);
        }
    }

    // Check if user wants to exit, assuming the input has been sanitized before
    private static boolean isExitCommand(String input) {
        return SmartOtterConstant.BYE_COMMAND.equalsIgnoreCase(input);
    }

    public static void main(String[] args) {
        greeting();

        try (Scanner scanner = new Scanner(System.in)) {
            String input;
            do {
                input = CustomIO.sanitizeInput(scanner.nextLine());
                respondToUser(input);
            } while (!isExitCommand(input));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
