package chatbox.main.tasks;

import chatbox.main.DateParser;
import java.time.LocalDateTime;
/**
 * Represents a task with a deadline.
 * Stores a description and a "by" date.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a Deadline task by parsing the given date string.
     *
     * @param description The task description.
     * @param by The deadline date string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = DateParser.parseDateTime(by);
        if (this.by == null) {
            throw new IllegalArgumentException("You key in the format that I don't understand! Please Use d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }

    /**
     * Returns the deadline date-time.
     *
     * @return The deadline as a LocalDateTime.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a user-friendly string representation of this task.
     *
     * @return The formatted task string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateParser.format(by) + ")";
    }

    /**
     * Serializes this task for storage.
     *
     * @return A pipe-delimited save string.
     */
    @Override
    public String toSaveString() {
        String status = isDone() ? "Done" : "Not done";
        return "Deadline | " + status + " | " + getDescription() + " | " + by.format(SAVE_FORMAT);
    }
}
