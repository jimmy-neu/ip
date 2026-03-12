package chatbox.main.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Represents a generic task in the ZhengjunChatbox.
 * A task consists of a description and a completion status.
 */
public abstract class Task {
    /**
     * Standard save format shared by all task types.
     */
    protected static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    protected String description;
    protected boolean isDone;

    /**
     * Initializes a new Task with the given description.
     * The task is marked as not done by default.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // default to false for new tasks
    }

    /**
     * Returns the status icon representing completion.
     *
     * @return "X" when done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return The description string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the completion state.
     *
     * @return true if done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Serializes this task into a single line for storage.
     *
     * @return A pipe-delimited save string.
     */
    public abstract String toSaveString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Compares this task to another object to check for equality.
     * Two tasks are considered equal if they are of the exact same class
     * and have the identical description.
     *
     * @param obj The object to compare against.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task otherTask = (Task) obj;
        return this.description.equalsIgnoreCase(otherTask.description);
    }
}
