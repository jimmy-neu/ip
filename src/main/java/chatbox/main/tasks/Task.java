package chatbox.main.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

     //Initializes a new Task
    public Task(String description) {
        this.description = description;
        this.isDone = false; // default to false for new tasks
    }

     //Returns an icon representing the status of the task. X for done Tasks

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}