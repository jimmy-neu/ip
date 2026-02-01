package chatbox.main;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * This Code Block Add the ability to store text and display them when requested
 */
public class ZhengjunChatbox {
    public static void main(String[] args) {
        String botName = "ZhengjunChatbox";
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine + "\nHello! I'm " + botName + "\nYou can enter text for me to store and key in command: List to view them. Enter bye to exit\n" + horizontalLine);
        // Level-2: Add logic
        ArrayList<String> taskList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(horizontalLine);
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
                System.out.println(horizontalLine);
            } else {
                // add a task to the list
                taskList.add(input);
                System.out.println(horizontalLine + "\nadded: " + input + "\n" + horizontalLine);
            }
        }
        // Exit message
        System.out.println(horizontalLine + "\nBye. Hope to see you again soon!\n" + horizontalLine);
        scanner.close();
    }
}
