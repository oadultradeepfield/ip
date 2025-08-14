public class SmartOtter {
    public static void main(String[] args) {
        String chatBotName = "SmartOtter";
        String greetingMessage = String.format("""
                ____________________________________________________________
                 Hello! I'm %s
                 What can I do for you?
                ____________________________________________________________
                 Bye. Hope to see you again soon!
                ____________________________________________________________
                
                """, chatBotName);
        System.out.println(greetingMessage);
    }
}
