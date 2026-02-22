package chatbox.main.commands;

import chatbox.main.*;
import chatbox.main.tasks.Task;
import chatbox.main.tasks.TaskList;

/**
 * Adds a new task (Todo, Deadline, or Event) to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    /**
     * Executes the command by adding the task to the list, saving the list to storage,
     * and returning a confirmation message.
     *
     * @param tasks   The list of tasks to which the new task will be added.
     * @param ui      The user interface for displaying messages (not directly used here as strings are returned).
     * @param storage The storage object used to persist the updated task list.
     * @return A string confirmation of the added task and the updated list size.
     * @throws ChatBoxException If there is an error during the saving process.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBoxException {
        tasks.add(task);
        storage.save(tasks.getAllTasks());
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}