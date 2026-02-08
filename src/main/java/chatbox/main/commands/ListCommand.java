package chatbox.main.commands;

import chatbox.main.Storage;
import chatbox.main.tasks.TaskList;
import chatbox.main.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage((i + 1) + "." + tasks.get(i));
        }
    }
}