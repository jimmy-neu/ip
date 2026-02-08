package chatbox.main.commands;

import chatbox.main.Storage;
import chatbox.main.tasks.TaskList;
import chatbox.main.Ui;
/**
 * Adds a new task (Todo, Deadline, or Event) to the task list.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}