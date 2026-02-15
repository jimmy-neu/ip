package chatbox.main.commands;

import chatbox.main.Storage;
import chatbox.main.Ui;
import chatbox.main.tasks.TaskList;

public class CheerCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showCheer();
    }
}