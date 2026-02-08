package chatbox.main;

import chatbox.main.tasks.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toString_correctFormat() {
        // Test that if the ToDo task prints correctly
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }
}