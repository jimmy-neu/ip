package chatbox.main;

import chatbox.main.commands.*;
import chatbox.main.tasks.Deadline;
import chatbox.main.tasks.Event;
import chatbox.main.tasks.ToDo;
import chatbox.main.commands.FindCommand;
import chatbox.main.commands.CheerCommand;

public class Parser {

    public static Command parse(String fullCommand) throws ChatBoxException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toUpperCase();

        switch (commandWord) {
            case "BYE":
                return new ExitCommand();
            case "LIST":
                return new ListCommand();
            case "DELETE":
                if (parts.length < 2) throw new ChatBoxException("Please specify which task number to delete.");
                return new DeleteCommand(Integer.parseInt(parts[1]) - 1);
            case "MARK":
                if (parts.length < 2) throw new ChatBoxException("Please specify which task number to mark.");
                return new ChatBoxException.MarkCommand(Integer.parseInt(parts[1]) - 1, true);
            case "UNMARK":
                if (parts.length < 2) throw new ChatBoxException("Please specify which task number to unmark.");
                return new ChatBoxException.MarkCommand(Integer.parseInt(parts[1]) - 1, false);
            case "TODO":
                if (parts.length < 2 || parts[1].trim().isEmpty()) throw new ChatBoxException("The description of a todo cannot be empty.");
                return new AddCommand(new ToDo(parts[1]));
            case "DEADLINE":
                if (parts.length < 2 || !parts[1].contains(" /by ")) throw new ChatBoxException("Deadlines need a description and /by date.");
                String[] dParts = parts[1].split(" /by ");
                return new AddCommand(new Deadline(dParts[0], dParts[1]));
            case "EVENT":
                if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) throw new ChatBoxException("Events need a description, /from, and /to.");
                String[] eParts = parts[1].split(" /from ");
                String[] times = eParts[1].split(" /to ");
                return new AddCommand(new Event(eParts[0], times[0], times[1]));
            case "FIND":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChatBoxException("The keyword for find cannot be empty as I will not know what to find");
                }
                return new FindCommand(parts[1].trim());
            case "CHEER":
                return new CheerCommand();
            default:
                throw new ChatBoxException("I'm sorry, but I don't know what that means :-(");
        }
    }
}