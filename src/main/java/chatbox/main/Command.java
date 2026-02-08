package chatbox.main;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBoxException;

    public boolean isExit() {
        return false;
    }
}