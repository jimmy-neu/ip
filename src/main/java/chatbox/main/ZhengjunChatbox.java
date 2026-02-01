package chatbox.main;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Use Enums.
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
                String[] parts = input.split(" ", 2);
                // Convert the first word to an Enum constant (case-insensitive)
                Command cmd;
                try {
                    cmd = Command.valueOf(parts[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    // This flags any command that isn't in our Enum list
                    throw new ChatBoxException("I am willing to help and I'm sorry, but I don't know what that means :-(. Could you be more specific");
                }
                // Use switch instead of if-else statements
                switch (cmd) {
                    case LIST:
                        System.out.println(horizontalLine + "\nHere are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println((i + 1) + "." + taskList.get(i));
                        }
                        System.out.println(horizontalLine);
                        break;

                    case MARK:
                    case UNMARK:
                        if (parts.length < 2) {
                            throw new ChatBoxException("I am really happy to help u update the list but can't do it without you specifying the number. Please specify the task number.");
                        }
                        int markIndex = Integer.parseInt(parts[1]) - 1;
                        if (cmd == Command.MARK) {
                            taskList.get(markIndex).markAsDone();
                            System.out.println(horizontalLine + "\nNice! I've marked this task as done:\n  "
                                    + taskList.get(markIndex) + "\n" + horizontalLine);
                        } else {
                            taskList.get(markIndex).unmarkAsDone();
                            System.out.println(horizontalLine + "\nOK, I've marked this task as not done yet:\n  "
                                    + taskList.get(markIndex) + "\n" + horizontalLine);
                        }
                        break;

                    case TODO:
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new ChatBoxException("I am willing to help but what stuff is to do. The description of a todo cannot be empty.");
                        }
                        Task newTodo = new ToDo(parts[1]);
                        taskList.add(newTodo);
                        System.out.println(horizontalLine + "\nGot it. I've added this task:\n  " + newTodo
                                + "\nNow you have " + taskList.size() + " tasks in the list.\n" + horizontalLine);
                        break;

                    case DEADLINE:
                        if (parts.length < 2 || !parts[1].contains(" /by ")) {
                            throw new ChatBoxException("I am willing to help and date is important for a deadline.Hence, a deadline must have a description and a /by time.");
                        }
                        String[] deadlineParts = parts[1].split(" /by ");
                        Task newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                        taskList.add(newDeadline);
                        System.out.println(horizontalLine + "\nGot it. I've added this task:\n  " + newDeadline
                                + "\nNow you have " + taskList.size() + " tasks in the list.\n" + horizontalLine);
                        break;

                    case EVENT:
                        if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
                            throw new ChatBoxException("I am willing to help and date is important for an event. Hence, an event must have a /from and a /to time.");
                        }
                        String[] eventParts = parts[1].split(" /from ");
                        String[] timeParts = eventParts[1].split(" /to ");
                        Task newEvent = new Event(eventParts[0], timeParts[0], timeParts[1]);
                        taskList.add(newEvent);
                        System.out.println(horizontalLine + "\nGot it. I've added this task:\n  " + newEvent
                                + "\nNow you have " + taskList.size() + " tasks in the list.\n" + horizontalLine);
                        break;

                    case DELETE:
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new ChatBoxException("I am eager to help you clean up, but I need a number! Please specify which task number to delete.");
                        }
                        int delIndex = Integer.parseInt(parts[1]) - 1;
                        Task removedTask = taskList.get(delIndex);
                        taskList.remove(delIndex);
                        System.out.println(horizontalLine + "\n Noted. I've removed this task:\n   "
                                + removedTask + "\n Now you have " + taskList.size() + " tasks in the list.\n" + horizontalLine);
                        break;

                    default:
                        // Note:The Enum valueOf catch handles most cases
                        throw new ChatBoxException("I am willing to help and I'm sorry, but I don't know what that means :-(.");
                }

            } catch (ChatBoxException e) {
                System.out.println(horizontalLine + "\n " + e.getMessage() + "\n" + horizontalLine);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println(horizontalLine + "\n Please enter a valid task number.\n" + horizontalLine);
            }
        }
        // Exit message
        System.out.println(horizontalLine + "\nBye. Hope to see you again soon!\n" + horizontalLine);
        scanner.close();
    }
}
