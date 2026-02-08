package chatbox.main.commands;

import chatbox.main.ChatBoxException;
import chatbox.main.Storage;
import chatbox.main.tasks.TaskList;
import chatbox.main.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBoxException;

    public boolean isExit() {
        return false;
    }
}