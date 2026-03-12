package chatbox.main;

import chatbox.main.tasks.Deadline;
import chatbox.main.tasks.Event;
import chatbox.main.tasks.Task;
import chatbox.main.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks to the hard disk.
 */
public class Storage {
    private final String filePath;

    /**
     * Initializes a Storage object with the specified file path.
     *
     * @param filePath The relative or absolute path to the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the hard disk.
     * If the file or directory does not exist, it will create them.
     *
     * @return An ArrayList containing the tasks loaded from the save file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                if (file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("OOPS!! I couldn't create the save file. Starting with an empty list.");
            }
            return loadedTasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    Task task = parseSavedTask(line);
                    if (task != null) {
                        loadedTasks.add(task);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping corrupted task line in save file.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("OOPS!! Save file not found. Starting with an empty list.");
        }
        return loadedTasks;
    }

    /**
     * Parses a single line from the save file and converts it into a Task object.
     * This helper method ensures the load method doesn't violate SLAP.
     *
     * @param line The string line read from the save file.
     * @return The corresponding Task object, or null if the line format is unknown.
     */
    private Task parseSavedTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null; // Skip corrupted lines
        }

        String type = parts[0];
        boolean isDone = parts[1].equalsIgnoreCase("Done") || parts[1].equals("1");
        String description = parts[2];
        Task task = null;

        switch (type) {
            case "Todo":
                task = new ToDo(description);
                break;
            case "Deadline":
                if (parts.length >= 4) {
                    task = new Deadline(description, parts[3]);
                }
                break;
            case "Event":
                if (parts.length >= 5) {
                    task = new Event(description, parts[3], parts[4]);
                }
                break;
            default:
                System.out.println("Unknown task type in file: " + type);
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Saves the current list of tasks to the hard disk.
     * Relies on the polymorphic toSaveString() method in the Task classes.
     *
     * @param tasks The ArrayList of tasks to be saved.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            try (FileWriter writer = new FileWriter(file)) {
                for (Task task : tasks) {
                    writer.write(task.toSaveString() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("There is a problem saving the file :(");
        }
    }
}
