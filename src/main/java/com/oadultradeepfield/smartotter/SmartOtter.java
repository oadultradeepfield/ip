package com.oadultradeepfield.smartotter;

import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.utility.CustomIO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Main class for the SmartOtter interactive console application.
 */
public class SmartOtter {
    private static final List<Task> tasks = new ArrayList<>();

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
        } else if (isListCommand(input)) {
            String result = IntStream.
                    range(0, tasks.size()).
                    mapToObj(i -> (i + 1) + ". " + tasks.get(i).toString()).
                    collect(Collectors.joining("\n"));
            CustomIO.printPretty(result);
        } else {
            tasks.add(new Task(input));
            CustomIO.printPretty("added: " + input);
        }
    }

    /**
     * Checks if input is a list command.
     * Expects input to be pre-sanitized.
     *
     * @param input sanitized user input
     * @return true if input matches exit command
     */
    private static boolean isListCommand(String input) {
        return SmartOtterConstant.LIST_COMMAND.equalsIgnoreCase(input);
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