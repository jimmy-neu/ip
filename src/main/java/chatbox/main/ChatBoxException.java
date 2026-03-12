package chatbox.main;

/**
 * Represents a custom exception specific to the ZhengjunChatbox.
 * This is used to signal errors related to user input or task operations.
 */
public class ChatBoxException extends Exception {
    /**
     * Creates a ChatBoxException with a user-facing error message.
     *
     * @param message The error message to display.
     */
    public ChatBoxException(String message) {
        super(message);
    }
}
