package chatbox.main.tasks;

import chatbox.main.DateParser;
import java.time.LocalDateTime;
/**
 * Represents an event task that occurs within a specific time range.
 * Stores a description, a start time (/from), and an end time (/to).
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an Event task by parsing the given date strings.
     *
     * @param description The task description.
     * @param from The start date string.
     * @param to The end date string.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = DateParser.parseDateTime(from);
        this.to = DateParser.parseDateTime(to);
        if (this.from == null || this.to == null) {
            throw new IllegalArgumentException("You key in the format that I don't understand! Please Use d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }

    /**
     * Returns the start date-time.
     *
     * @return The start as a LocalDateTime.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end date-time.
     *
     * @return The end as a LocalDateTime.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns a user-friendly string representation of this task.
     *
     * @return The formatted task string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateParser.format(from) + " to: " + DateParser.format(to) + ")";
    }

    /**
     * Serializes this task for storage.
     *
     * @return A pipe-delimited save string.
     */
    @Override
    public String toSaveString() {
        String status = isDone() ? "Done" : "Not done";
        return "Event | " + status + " | " + getDescription()
                + " | " + from.format(SAVE_FORMAT) + " | " + to.format(SAVE_FORMAT);
    }
}
