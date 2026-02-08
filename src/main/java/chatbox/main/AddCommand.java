package chatbox.main;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBoxException {
        tasks.add(task);
        storage.save(tasks.getAllTasks()); // Save immediately
        ui.showMessage("Got it. I've added this task:\n  " + task);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }
}