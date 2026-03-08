package chatbox.main;

import chatbox.main.commands.*;
import chatbox.main.tasks.Deadline;
import chatbox.main.tasks.Event;
import chatbox.main.tasks.ToDo;
import chatbox.main.commands.FindCommand;
import chatbox.main.commands.CheerCommand;

/**
 * Parses user input into commands for execution.
 * contains methods to interpret user commands and arguments.
 */
public class Parser {

    public static Command parse(String userInput) throws ChatBoxException {
        assert userInput != null : "userInput should not be null";

        String[] parts = userInput.split(" ", 2);

        assert parts.length > 0 : "Split should result in at least one part";

        String commandWord = parts[0].toUpperCase();

        switch (commandWord) {
            case "BYE":
                return new ExitCommand();
            case "LIST":
                return new ListCommand();
            case "DELETE":
                if (parts.length < 2) {
                    throw new ChatBoxException("Nani?! You need to tell me which mission number to delete from the scroll!");
                }
                return new DeleteCommand(Integer.parseInt(parts[1]) - 1);
            case "MARK":
                if (parts.length < 2) {
                    throw new ChatBoxException("Nani?! Which mission did you complete? Give me the task number!");
                }
                return new MarkCommand(Integer.parseInt(parts[1]) - 1, true);
            case "UNMARK":
                if (parts.length < 2) {
                    throw new ChatBoxException("Nani?! Which mission is incomplete? Give me the task number!");
                }
                return new MarkCommand(Integer.parseInt(parts[1]) - 1, false);
            case "TODO":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChatBoxException("Nani?! The description of a D-Rank mission (todo) cannot be empty!");
                }
                return new AddCommand(new ToDo(parts[1]));
            case "DEADLINE":
                if (parts.length < 2 || !parts[1].contains(" /by ")) {
                    throw new ChatBoxException("Nani?! Deadlines require a description and a /by time limit!");
                }
                String[] dParts = parts[1].split(" /by ");
                assert dParts.length == 2 : "Deadline split failed";
                return new AddCommand(new Deadline(dParts[0], dParts[1]));
            case "EVENT":
                if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
                    throw new ChatBoxException("Nani?! Events require a description, a /from start time, and a /to end time!");
                }
                String[] eParts = parts[1].split(" /from ");
                assert eParts.length == 2 : "Event /from split failed";

                String[] times = eParts[1].split(" /to ");
                assert times.length == 2 : "Event /to split failed";

                return new AddCommand(new Event(eParts[0], times[0], times[1]));
            case "FIND":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChatBoxException("Nani?! You need to tell me what keyword to search for in the archives!");
                }
                return new FindCommand(parts[1].trim());
            case "CHEER":
                return new CheerCommand();
            default:
                throw new ChatBoxException("Nani?! I've never seen that jutsu before. What does that mean?");
        }
    }
}