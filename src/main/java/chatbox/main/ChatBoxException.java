package chatbox.main;

import chatbox.main.commands.Command;
import chatbox.main.tasks.Task;
import chatbox.main.tasks.TaskList;

/**
 * A custom exception class for the ZhengjunChatbox.
 */
public class ChatBoxException extends Exception {
    public ChatBoxException(String message) {
        super(message);
    }

    public static class MarkCommand extends Command {
        private final int index;
        private final boolean isMark;

        public MarkCommand(int index, boolean isMark) {
            this.index = index;
            this.isMark = isMark;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBoxException {
            try {
                Task task = tasks.get(index);
                if (isMark) {
                    task.markAsDone();
                    ui.showMessage("Great job! I've marked this task as done:\n  " + task);
                } else {
                    task.unmarkAsDone();
                    ui.showMessage("OK take your time, I've marked this task as not done yet:\n  " + task);
                }
                storage.save(tasks.getAllTasks());
            } catch (IndexOutOfBoundsException e) {
                throw new ChatBoxException("Invalid task number. Could you check the list again?");
            }
        }
    }
}