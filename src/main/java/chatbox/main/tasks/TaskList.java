package chatbox.main.tasks;

import java.util.ArrayList;
/**
 * Manages the list of tasks.
 * Provides methods to add, delete, mark, unmark, and search for tasks within the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list from an existing list of tasks.
     *
     * @param tasks The tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the given index.
     *
     * @param index The zero-based index of the task to remove.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the task at the given index.
     *
     * @param index The zero-based index of the task.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The task count.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return The tasks as an ArrayList.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Checks whether a duplicate task already exists in the list.
     *
     * @param newTask The task to check.
     * @return true if a duplicate exists, false otherwise.
     */
    public boolean hasDuplicate(Task newTask) {
        // A-Streams feature: Any match returns true if equals() evaluates to true
        return tasks.stream().anyMatch(task -> task.equals(newTask));
    }

    /**
     * Finds tasks whose descriptions contain the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A formatted string of matching tasks.
     */
    public String findTasks(String keyword) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        sb.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            // Check if the description contains the keyword
            if (task.getDescription().contains(keyword)) {
                count++;
                // Append the matching task to the result string
                sb.append(count).append(".").append(task.toString()).append("\n");
            }
        }

        if (count == 0) {
            return "No matching tasks found.";
        }

        return sb.toString();
    }
}
