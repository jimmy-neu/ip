package chatbox.main;

import chatbox.main.tasks.Deadline;
import chatbox.main.tasks.Event;
import chatbox.main.tasks.Task;
import chatbox.main.tasks.ToDo;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void saveAndLoad_roundTrip_preservesTasks() throws Exception {
        Path tempFile = Files.createTempFile("chatbox", ".txt");
        Files.deleteIfExists(tempFile);

        Storage storage = new Storage(tempFile.toString());
        ArrayList<Task> tasks = new ArrayList<>();

        ToDo todo = new ToDo("read book");
        Deadline deadline = new Deadline("submit report", "2/12/2019 1800");
        Event event = new Event("team meeting", "3/12/2019 0900", "3/12/2019 1000");
        deadline.markAsDone();

        tasks.add(todo);
        tasks.add(deadline);
        tasks.add(event);

        storage.save(tasks);
        ArrayList<Task> loaded = storage.load();

        assertEquals(tasks.size(), loaded.size());
        for (int i = 0; i < tasks.size(); i++) {
            assertEquals(tasks.get(i).toSaveString(), loaded.get(i).toSaveString());
        }

        Files.deleteIfExists(tempFile);
    }
}
