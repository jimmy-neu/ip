package chatbox.main.commands;

import chatbox.main.Storage;
import chatbox.main.tasks.TaskList;
import chatbox.main.Ui;

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