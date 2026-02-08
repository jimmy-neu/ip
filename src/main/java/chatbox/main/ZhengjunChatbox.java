package chatbox.main;

import chatbox.main.commands.Command;
import chatbox.main.tasks.TaskList;

public class ZhengjunChatbox {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // Sets up the tools and tries to load the save file
    public ZhengjunChatbox(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    // The Command Loop: Keeps running until the user says "bye"
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();

                //Parse the text into a Command object
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();

            } catch (ChatBoxException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                // Catches other unexpected errors
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new ZhengjunChatbox("data/ChatboxMemory.txt").run();
    }
}