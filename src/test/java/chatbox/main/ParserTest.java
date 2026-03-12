package chatbox.main;

import chatbox.main.commands.AddCommand;
import chatbox.main.commands.Command;
import chatbox.main.commands.ExitCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    // Positive Test Case: Verifies that "bye" returns an ExitCommand
    @Test
    public void parse_byeCommand_success() throws Exception {
        Command c = Parser.parse("bye");
        assertTrue(c instanceof ExitCommand);
    }

    // Positive Test Case: Verifies that "todo read" returns an AddCommand
    @Test
    public void parse_todoCommand_success() throws Exception {
        Command c = Parser.parse("todo read book");
        assertTrue(c instanceof AddCommand);
    }

    // Negative Test Case: Verifies that empty todo throws an exception
    @Test
    public void parse_emptyTodo_exceptionThrown() {
        ChatBoxException e = assertThrows(ChatBoxException.class, () -> Parser.parse("todo"));
        assertEquals("Nani?! The description of a D-Rank mission (todo) cannot be empty!", e.getMessage());
    }

    // Positive Test Case: Verifies that "deadline" returns an AddCommand
    @Test
    public void parse_deadlineCommand_success() throws Exception {
        Command c = Parser.parse("deadline submit report /by 2/12/2019 1800");
        assertTrue(c instanceof AddCommand);
    }

    // Negative Test Case: Verifies that an unknown command throws an exception
    @Test
    public void parse_unknownCommand_exceptionThrown() {
        assertThrows(ChatBoxException.class, () -> Parser.parse("dance"));
    }
}
