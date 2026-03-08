package chatbox.main;

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * Handles user interface interactions.
 * Responsible for formatting messages to be displayed by the GUI.
 */
public class Ui {

    public Ui() {
    }

    public String showWelcome() {
        return "Welcome to the Hidden Leaf Training Grounds, Naruto!\n" +
                "I am Instructor Iruka.\n" +
                "What D-Rank missions or tasks shall we log today?";
    }

    public String showLoadingError() {
        return "OOPS!! I tried to load your file but failed. Starting with an empty list.";
    }

    public String showError(String message) {
        return "OOPS There is an error!! " + message;
    }

    public String showMessage(String message) {
        return message;
    }

    public String showCheer() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("data", "quotes.txt"));

            if (lines.isEmpty()) {
                return "The quote file is empty!";
            }
            Random random = new Random();
            return lines.get(random.nextInt(lines.size()));

        } catch (IOException e) {
            return "Could not load quotes. Make sure 'data/quotes.txt' exists!";
        }
    }
}