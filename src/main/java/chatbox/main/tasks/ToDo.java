package chatbox.main.tasks;

/**
 * Represents a "Todo" task, which is a basic task without any date or time attached.
 */
public class ToDo extends Task {

    /**
     * Initializes a new ToDo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a user-friendly string representation of this task.
     *
     * @return The formatted task string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Serializes this task for storage.
     *
     * @return A pipe-delimited save string.
     */
    @Override
    public String toSaveString() {
        String status = isDone() ? "Done" : "Not done";
        return "Todo | " + status + " | " + getDescription();
    }
}
