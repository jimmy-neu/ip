package chatbox.main;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * This Code Block further add the ability to mark and unmark the tasks in the list
 */
public class ZhengjunChatbox {
    public static void main(String[] args) {
        String botName = "ZhengjunChatbox";
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine + "\nHello! I'm " + botName + "\nYou can enter text for me to store and key in command: List to view them. \nMoreover, you can mark and unmark tasks. Enter bye to exit\n" + horizontalLine);

        ArrayList<Task> taskList = new ArrayList<>(); // The Arraylist now store Task class
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" "); // Splits the input
            String command = parts[0]; // Obtain the command

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(horizontalLine + "\nHere are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + "." + taskList.get(i));
                }
                System.out.println(horizontalLine);

            } else if (command.equals("mark")) {
                int index = Integer.parseInt(parts[1]) - 1; // Convert user input to 0-based index
                taskList.get(index).markAsDone();
                System.out.println(horizontalLine + "\nNice! I've marked this task as done:\n  "
                        + taskList.get(index) + "\n" + horizontalLine);

            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                taskList.get(index).unmarkAsDone();
                System.out.println(horizontalLine + "\nOK, I've marked this task as not done yet:\n  "
                        + taskList.get(index) + "\n" + horizontalLine);

            } else {
                taskList.add(new Task(input));
                System.out.println(horizontalLine + "\nadded: " + input + "\n" + horizontalLine);
            }
        }
        // Exit message
        System.out.println(horizontalLine + "\nBye. Hope to see you again soon!\n" + horizontalLine);
        scanner.close();
    }
}
