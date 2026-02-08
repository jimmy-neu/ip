package chatbox.main;

import chatbox.main.commands.Command;
import chatbox.main.commands.ExitCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parse_byeCommand_returnsExitCommand() throws Exception {
        // Test that "bye" returns an ExitCommand object
        Command c = Parser.parse("bye");
        assertTrue(c instanceof ExitCommand);
    }
}