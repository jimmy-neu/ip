package chatbox.main;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * This further add the ability to support ToDo, Deadline and  Event.
 */
public class ZhengjunChatbox {
    public static void main(String[] args) {
        String botName = "ZhengjunChatbox";
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine + "\nHello! I'm " + botName + "\nYou can enter text for me to store and key in command: List to view them. \nMoreover, you can mark and unmark tasks. \nThere are three types of input: Todo, Deadline and Event. Enter bye to exit\n" + horizontalLine);

        ArrayList<Task> taskList = new ArrayList<>(); // The Arraylist now store Task class
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2); // Split the command from the rest of the string
            String command = parts[0];

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(horizontalLine + "\nHere are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + "." + taskList.get(i));
                }
                System.out.println(horizontalLine);

            } else if (command.equals("mark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                taskList.get(index).markAsDone();
                System.out.println(horizontalLine + "\nNice! I've marked this task as done:\n  "
                        + taskList.get(index) + "\n" + horizontalLine);

            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                taskList.get(index).unmarkAsDone();
                System.out.println(horizontalLine + "\nOK, I've marked this task as not done yet:\n  "
                        + taskList.get(index) + "\n" + horizontalLine);

            } else if (command.equals("todo")) {
                Task newTask = new ToDo(parts[1]);
                taskList.add(newTask);
                System.out.println(horizontalLine + "\nGot it. I've added this task:\n  " + newTask
                        + "\nNow you have " + taskList.size() + " tasks in the list.\n" + horizontalLine);

            } else if (command.equals("deadline")) {
                // Split the input into smaller parts
                // For instance, "return book /by Sunday" can be splited into ["return book", "Sunday"]
                String[] deadlineParts = parts[1].split(" /by ");
                Task newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
                taskList.add(newTask);
                System.out.println(horizontalLine + "\nGot it. I've added this task:\n  " + newTask
                        + "\nNow you have " + taskList.size() + " tasks in the list.\n" + horizontalLine);

            } else if (command.equals("event")) {
                String[] eventParts = parts[1].split(" /from ");
                String[] timeParts = eventParts[1].split(" /to ");
                Task newTask = new Event(eventParts[0], timeParts[0], timeParts[1]);
                taskList.add(newTask);
                System.out.println(horizontalLine + "\nGot it. I've added this task:\n  " + newTask
                        + "\nNow you have " + taskList.size() + " tasks in the list.\n" + horizontalLine);

            } else {
                // This handles cases where no specific command matches.
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println(horizontalLine + "\nadded: " + input + "\n" + horizontalLine);
            }
        }
        // Exit message
        System.out.println(horizontalLine + "\nBye. Hope to see you again soon!\n" + horizontalLine);
        scanner.close();
    }
}
