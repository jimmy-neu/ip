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
        // Ninja-themed loading error
        return "Nani?! I tried to unroll your mission scrolls but failed. Starting with an empty scroll.";
    }

    public String showError(String message) {
        return message;
    }

    public String showMessage(String message) {
        return message;
    }

    public String showCheer() {
        try {
            java.io.InputStream is = this.getClass().getResourceAsStream("/texts/quotes.txt");
            if (is == null) {
                return "Nani?! I lost the quote scroll!";
            }

            java.util.Scanner scanner = new java.util.Scanner(is);
            java.util.List<String> lines = new java.util.ArrayList<>();

            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();

            if (lines.isEmpty()) {
                return "The quote scroll is empty!";
            }
            java.util.Random random = new java.util.Random();
            return lines.get(random.nextInt(lines.size()));

        } catch (Exception e) {
            return "Nani?! Could not unroll the quotes.txt scroll!";
        }
    }
}