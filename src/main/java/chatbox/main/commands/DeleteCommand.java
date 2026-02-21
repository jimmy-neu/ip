package chatbox.main.commands;

import chatbox.main.*;
import chatbox.main.tasks.Task;
import chatbox.main.tasks.TaskList;
/**
 * Executes the delete command.
 * Removes the task from the task list and then saves the updated list to storage,
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBoxException {
        try {
            Task removed = tasks.get(index);
            tasks.delete(index);
            storage.save(tasks.getAllTasks());
            return "Noted. I've removed this task:\n  " + removed +
                    "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBoxException("Invalid task number.");
        }
    }
}