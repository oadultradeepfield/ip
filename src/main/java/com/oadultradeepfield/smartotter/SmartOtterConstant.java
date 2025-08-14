package com.oadultradeepfield.smartotter;

/**
 * Constants used throughout the SmartOtter application.
 * This class cannot be instantiated.
 */
public final class SmartOtterConstant {
    /**
     * Command to list all tasks
     */
    public static final String LIST_COMMAND = "list";

    /**
     * Command to exit the application
     */
    public static final String BYE_COMMAND = "bye";

    public static final String APP_NAME = "SmartOtter 🦦📚";
    public static final String HORIZONTAL_BAR = "____________________________________________________________";

    /**
     * Welcome message displayed when the application starts
     */
    public static final String GREETING_MESSAGE_TEMPLATE = """
            Hello! I'm %s
            I can help you with anything,
            as long as you give me fish 😋""".formatted(APP_NAME);

    /**
     * Message displayed when user exits the application
     */
    public static final String FAREWELL_MESSAGE = "Bye. Hope to eat more fish soon!";

    // Prevents instantiation
    private SmartOtterConstant() {
    }
}