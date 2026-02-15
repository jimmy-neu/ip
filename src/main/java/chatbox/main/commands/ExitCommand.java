package chatbox.main.commands;

import chatbox.main.Storage;
import chatbox.main.tasks.TaskList;
import chatbox.main.Ui;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Return the goodbye message so it appears in the GUI
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}