package chatbox.main;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Handles user interface interactions.
 * Responsible for formatting messages to be displayed by the GUI.
 */
public class Ui {

    /**
     * Creates a Ui helper for formatting user-visible messages.
     */
    public Ui() {
    }

    /**
     * Returns the welcome message shown on startup.
     *
     * @return The welcome message string.
     */
    public String showWelcome() {
        return "Welcome to the Hidden Leaf Training Grounds, Naruto!\n" +
                "I am Instructor Iruka.\n" +
                "What D-Rank missions or tasks shall we log today?";
    }

    /**
     * Returns a loading error message.
     *
     * @return The loading error message string.
     */
    public String showLoadingError() {
        // Ninja-themed loading error
        return "Nani?! I tried to unroll your mission scrolls but failed. Starting with an empty scroll.";
    }

    /**
     * Formats an error message for display.
     *
     * @param message The error message to display.
     * @return The formatted error string.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Returns a general message for display.
     *
     * @param message The message to display.
     * @return The formatted message string.
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Returns a random motivational cheer from the bundled quote list.
     *
     * @return A motivational message, or a fallback if unavailable.
     */
    public String showCheer() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/texts/quotes.txt");
            if (is == null) {
                return "Nani?! I lost the quote scroll!";
            }

            Scanner scanner = new Scanner(is);
            List<String> lines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();

            if (lines.isEmpty()) {
                return "The quote scroll is empty!";
            }
            Random random = new Random();
            return lines.get(random.nextInt(lines.size()));

        } catch (Exception e) {
            return "Nani?! Could not unroll the quotes.txt scroll!";
        }
    }
}
