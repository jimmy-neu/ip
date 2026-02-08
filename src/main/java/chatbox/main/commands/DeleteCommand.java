package chatbox.main.commands;

import chatbox.main.*;
import chatbox.main.tasks.Task;
import chatbox.main.tasks.TaskList;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBoxException {
        try {
            Task removed = tasks.get(index);
            tasks.delete(index);
            storage.save(tasks.getAllTasks());
            ui.showMessage("Noted. I've removed this task:\n  " + removed);
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBoxException("Invalid task number. Could you check the list again?");
        }
    }
}