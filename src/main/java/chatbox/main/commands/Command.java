package chatbox.main.commands;

import chatbox.main.ChatBoxException;
import chatbox.main.Storage;
import chatbox.main.tasks.TaskList;
import chatbox.main.Ui;
/**
 * Represents an abstract command that can be executed by the chatbox.
 * This class serves as a base for all specific command types (e.g., AddCommand, ExitCommand).
 */
public abstract class Command {
    /**
     * Executes this command against the given task list, UI, and storage.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI helper for formatting responses.
     * @param storage The storage to persist changes.
     * @return The response string to display.
     * @throws ChatBoxException If command execution fails.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBoxException;

    /**
     * Indicates whether this command should terminate the application.
     *
     * @return true if this command exits the program, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
