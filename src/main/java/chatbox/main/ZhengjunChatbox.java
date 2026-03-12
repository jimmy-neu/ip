package chatbox.main;

import chatbox.main.commands.Command;
import chatbox.main.tasks.TaskList;

/**
 * The main logic class for the chatbot.
 * Now acts as the "brain" that receives input from the GUI and returns responses.
 */
public class ZhengjunChatbox {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a chatbox instance using the default save file.
     */
    public ZhengjunChatbox() {
        this("data/ChatboxMemory.txt");
    }

    /**
     * Creates a chatbox instance with a custom save file path.
     *
     * @param filePath The save file path to use.
     */
    public ZhengjunChatbox(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            // If loading fails, start with an empty list
            tasks = new TaskList();
        }
    }

    /**
     * Returns the welcome message.
     *
     * @return The welcome message string.
     */
    public String getWelcome() {
        return ui.showWelcome();
    }

    /**
     * Processes user input and returns a response message.
     *
     * @param input The raw user input string.
     * @return The response message to display.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage); // Returns the result String to the GUI
        } catch (ChatBoxException e) {
            return ui.showError(e.getMessage());
        } catch (NumberFormatException e) {
            return ui.showError("Nani?! That's not a number! Please enter a valid mission ID.");
        } catch (Exception e) {
            return ui.showError("Nani?! An unknown jutsu interference occurred: " + e.getMessage());
        }
    }
}
