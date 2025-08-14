public final class SmartOtterConstant {
    // Private constructor to prevent initialization
    private SmartOtterConstant() {}

    // Template string for constructing responses
    public static final String APP_NAME = "SmartOtter 🦦📚";
    public static final String HORIZONTAL_BAR = "____________________________________________________________";

    public static final String GREETING_MESSAGE_TEMPLATE = """
             Hello! I'm %s
             I can help you with anything, as long as you give me fish 😋
            """.formatted(APP_NAME);

    public static final String FAREWELL_MESSAGE = """ 
             Bye. Hope to eat more fish soon!
            """;
}
