package chatbox.main;

import chatbox.main.tasks.TaskList;
import chatbox.main.tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    @Test
    public void add_addsTaskAndIncrementsSize() {
        TaskList list = new TaskList();
        list.add(new ToDo("read book"));
        assertEquals(1, list.size());
    }

    @Test
    public void hasDuplicate_sameDescriptionDifferentCase_returnsTrue() {
        TaskList list = new TaskList();
        list.add(new ToDo("read book"));
        assertTrue(list.hasDuplicate(new ToDo("READ BOOK")));
    }

    @Test
    public void findTasks_keywordMatches_returnsOnlyMatches() {
        TaskList list = new TaskList();
        list.add(new ToDo("read book"));
        list.add(new ToDo("write essay"));

        String result = list.findTasks("read");
        assertTrue(result.contains("read book"));
        assertFalse(result.contains("write essay"));
    }

    @Test
    public void findTasks_noMatches_returnsNoMatchMessage() {
        TaskList list = new TaskList();
        list.add(new ToDo("read book"));
        String result = list.findTasks("swim");
        assertEquals("No matching tasks found.", result);
    }
}
