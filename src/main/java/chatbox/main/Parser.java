package chatbox.main;

import chatbox.main.commands.*;
import chatbox.main.tasks.Deadline;
import chatbox.main.tasks.Event;
import chatbox.main.tasks.ToDo;

/**
 * Parses user input and determines the appropriate command to execute.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command to execute.
     *
     * @param userInput The full string input from the user.
     * @return The Command object representing the user's instruction.
     * @throws ChatBoxException If the user input is incomplete or formatted incorrectly.
     */
    public static Command parse(String userInput) throws ChatBoxException {
        assert userInput != null : "userInput should not be null";
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0].toUpperCase();

        switch (commandWord) {
            case "BYE":
                return new ExitCommand();
            case "LIST":
                return new ListCommand();
            case "DELETE":
                return parseDelete(parts);
            case "MARK":
                return parseMark(parts, true);
            case "UNMARK":
                return parseMark(parts, false);
            case "TODO":
                return parseTodo(parts);
            case "DEADLINE":
                return parseDeadline(parts);
            case "EVENT":
                return parseEvent(parts);
            case "FIND":
                return parseFind(parts);
            case "CHEER":
                return new CheerCommand();
            default:
                throw new ChatBoxException("Nani?! I've never seen that jutsu before. What does that mean?");
        }
    }

    /**
     * Parses the arguments for a delete command.
     *
     * @param parts The split user input array.
     * @return A DeleteCommand initialized with the target task index.
     * @throws ChatBoxException If the mission number is missing.
     */
    private static Command parseDelete(String[] parts) throws ChatBoxException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ChatBoxException("Nani?! You need to tell me which mission number to delete from the scroll!");
        }
        int index = Integer.parseInt(parts[1].trim()) - 1;
        return new DeleteCommand(index);
    }

    /**
     * Parses the arguments for a mark or unmark command.
     *
     * @param parts The split user input array.
     * @param isMark True if marking as done, false if unmarking.
     * @return A MarkCommand initialized with the target task index.
     * @throws ChatBoxException If the task number is missing.
     */
    private static Command parseMark(String[] parts, boolean isMark) throws ChatBoxException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            String errorMsg = isMark ? "Nani?! Which mission did you complete? Give me the task number!"
                    : "Nani?! Which mission is incomplete? Give me the task number!";
            throw new ChatBoxException(errorMsg);
        }
        int index = Integer.parseInt(parts[1].trim()) - 1;
        return new MarkCommand(index, isMark);
    }

    /**
     * Parses the arguments for a todo command.
     *
     * @param parts The split user input array.
     * @return An AddCommand containing the new ToDo task.
     * @throws ChatBoxException If the description is empty.
     */
    private static Command parseTodo(String[] parts) throws ChatBoxException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ChatBoxException("Nani?! The description of a D-Rank mission (todo) cannot be empty!");
        }
        return new AddCommand(new ToDo(parts[1].trim()));
    }

    /**
     * Parses the arguments for a deadline command.
     *
     * @param parts The split user input array.
     * @return An AddCommand containing the new Deadline task.
     * @throws ChatBoxException If the description or /by time is missing.
     */
    private static Command parseDeadline(String[] parts) throws ChatBoxException {
        if (parts.length < 2 || !parts[1].contains(" /by ")) {
            throw new ChatBoxException("Nani?! Deadlines require a description and a /by time limit!");
        }
        String[] dParts = parts[1].split(" /by ");
        return new AddCommand(new Deadline(dParts[0].trim(), dParts[1].trim()));
    }

    /**
     * Parses the arguments for an event command.
     *
     * @param parts The split user input array.
     * @return An AddCommand containing the new Event task.
     * @throws ChatBoxException If the description, /from, or /to times are missing.
     */
    private static Command parseEvent(String[] parts) throws ChatBoxException {
        if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
            throw new ChatBoxException("Nani?! Events require a description, a /from start time, and a /to end time!");
        }
        String description = parts[1].split(" /from ")[0].trim();
        String times = parts[1].split(" /from ")[1];
        String fromDate = times.split(" /to ")[0].trim();
        String toDate = times.split(" /to ")[1].trim();
        return new AddCommand(new Event(description, fromDate, toDate));
    }

    /**
     * Parses the arguments for a find command.
     *
     * @param parts The split user input array.
     * @return A FindCommand initialized with the search keyword.
     * @throws ChatBoxException If the keyword is missing.
     */
    private static Command parseFind(String[] parts) throws ChatBoxException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ChatBoxException("Nani?! You need to tell me what keyword to search for in the archives!");
        }
        return new FindCommand(parts[1].trim());
    }
}
