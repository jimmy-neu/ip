package chatbox.main;

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Ui {
    private final Scanner scanner;
    private final String HORIZONTAL_LINE = "____________________________________________________________";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm ZhengjunChatbox");
        System.out.println("What can I do for you?");
        System.out.println("You can enter text for me to store and key in command: List to view them.");
        System.out.println("Moreover, you can mark and unmark tasks.");
        System.out.println("There are three types of input: Todo, Deadline and Event. Enter bye to exit");
        showLine();
    }

    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void showLoadingError() {
        System.out.println("OOPS!! I tried to load your file but failed. Starting with an empty list.");
    }

    public void showError(String message) {
        System.out.println("OOPS There is an error!! " + message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
    public void showCheer() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("data", "quotes.txt"));

            if (lines.isEmpty()) {
                showLine();
                System.out.println("The quote file is empty!");
                showLine();
                return;
            }
            // Pick a random line
            Random random = new Random();
            String randomQuote = lines.get(random.nextInt(lines.size()));

            showLine();
            System.out.println(randomQuote);
            showLine();

        } catch (IOException e) {
            showLine();
            System.out.println("Could not load quotes. Make sure 'data/quotes.txt' exists!");
            showLine();
        }
    }
}