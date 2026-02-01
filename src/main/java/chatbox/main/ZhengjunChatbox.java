package chatbox.main;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * This further add the ability to support ToDo, Deadline and  Event.
 * * It handles various user input errors, including:
 * - Empty descriptions for tasks (e.g., "todo").
 * - Missing time parameters for deadlines and events (e.g., missing /by, /from, or /to).
 * - Invalid task numbering during marking or unmarking (e.g., non-integers or out-of-bounds).
 * - Unrecognized commands.
 */

public class ZhengjunChatbox {
    public static void main(String[] args) {
        String botName = "ZhengjunChatbox";
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm " + botName);
        System.out.println("You can enter text for me to store and key in command: List to view them.");
        System.out.println("Moreover, you can mark and unmark tasks.");
        System.out.println("There are three types of input: Todo, Deadline and Event. Enter bye to exit");
        System.out.println(horizontalLine);

        ArrayList<Task> taskList = new ArrayList<>(); // The Arraylist now store Task class
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }

            try {
                String[] parts = input.split(" ", 2); // Split the command from the rest of the string
                String command = parts[0];

                if (command.equals("list")) {
                    System.out.println(horizontalLine + "\nHere are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i + 1) + "." + taskList.get(i));
                    }
                    System.out.println(horizontalLine);

                } else if (command.equals("mark") || command.equals("unmark")) {
                    // ERROR: Check if task number is provided
                    if (parts.length < 2) {
                        throw new ChatBoxException("I am really happy to help u update the list but can't do it without you specifying the number. Please specify the task number to " + command + ".");
                    }
                    int index = Integer.parseInt(parts[1]) - 1;

                    if (command.equals("mark")) {
                        taskList.get(index).markAsDone();
                        System.out.println(horizontalLine + "\nNice! I've marked this task as done:\n  "
                                + taskList.get(index) + "\n" + horizontalLine);
                    } else {
                        taskList.get(index).unmarkAsDone();
                        System.out.println(horizontalLine + "\nOK, I've marked this task as not done yet:\n  "
                                + taskList.get(index) + "\n" + horizontalLine);
                    }

                } else if (command.equals("todo")) {
                    // ERROR: Check if description is empty
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new ChatBoxException("I am willing to help but what stuff is to do. The description of a todo cannot be empty.");
                    }
                    Task newTask = new ToDo(parts[1]);
                    taskList.add(newTask);
                    System.out.println(horizontalLine + "\nGot it. I've added this task:\n  " + newTask
                            + "\nNow you have " + taskList.size() + " tasks in the list.\n" + horizontalLine);

                } else if (command.equals("deadline")) {
                    // ERROR: Check for description and /by
                    if (parts.length < 2 || !parts[1].contains(" /by ")) {
                        throw new ChatBoxException("I am willing to help and date is important for a deadline.Hence, a deadline must have a description and a /by time.");
                    }
                    // Split the input into smaller parts
                    // For instance, "return book /by Sunday" can be splited into ["return book", "Sunday"]
                    String[] deadlineParts = parts[1].split(" /by ");
                    Task newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
                    taskList.add(newTask);
                    System.out.println(horizontalLine + "\nGot it. I've added this task:\n  " + newTask
                            + "\nNow you have " + taskList.size() + " tasks in the list.\n" + horizontalLine);

                } else if (command.equals("event")) {
                    // ERROR: Check for /from and /to
                    if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
                        throw new ChatBoxException("I am willing to help and date is important for an event. Hence, an event must have a /from and a /to time.");
                    }
                    String[] eventParts = parts[1].split(" /from ");
                    String[] timeParts = eventParts[1].split(" /to ");
                    Task newTask = new Event(eventParts[0], timeParts[0], timeParts[1]);
                    taskList.add(newTask);
                    System.out.println(horizontalLine + "\nGot it. I've added this task:\n  " + newTask
                            + "\nNow you have " + taskList.size() + " tasks in the list.\n" + horizontalLine);

                } else {
                    // ERROR: Unknown command
                    throw new ChatBoxException("I am willing to help and I'm sorry, but I don't know what that means :-(. Could you be more specific");
                }

            } catch (ChatBoxException e) {
                System.out.println(horizontalLine + "\n " + e.getMessage() + "\n" + horizontalLine);
            } catch (NumberFormatException e) {
                System.out.println(horizontalLine + "\n Please enter a valid task number.\n" + horizontalLine);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(horizontalLine + "\n That task number does not exist. Do not get ahead of yourself\n" + horizontalLine);
            }
        }
        // Exit message
        System.out.println(horizontalLine + "\nBye. Hope to see you again soon!\n" + horizontalLine);
        scanner.close();
    }
}
