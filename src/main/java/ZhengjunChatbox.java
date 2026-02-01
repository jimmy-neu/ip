package chatbot.main;
import java.util.Scanner;

/**
 * This Code Block echoes input until "bye" is received.
 */
public class ZhengjunChatbox {
    public static void main(String[] args) {
        String botName = "ZhengjunChatbox";
        String horizontalLine = "____________________________________________________________";

        System.out.println(horizontalLine + "\nHello! I'm " + botName + "\nWhat can I do for you?\n" + horizontalLine);
        Scanner scanner = new Scanner(System.in);
        String input = "";
        // Level-1: Echo loop logic
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }

            System.out.println(horizontalLine + "\n" + input + "\n" + horizontalLine);
        }
        // Exit message
        System.out.println(horizontalLine + "\nBye. Hope to see you again soon!\n" + horizontalLine);
        scanner.close();
    }
}