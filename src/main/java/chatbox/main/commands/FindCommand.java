package chatbox.main.commands;

import chatbox.main.Storage;
import chatbox.main.Ui;
import chatbox.main.tasks.TaskList;

/**
 * Represents a command to find tasks by searching for a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.findTasks(keyword);
    }
}