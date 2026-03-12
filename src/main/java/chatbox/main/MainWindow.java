package chatbox.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * Controller for the main chat window.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ZhengjunChatbox chatbox;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image zhengjunImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

    /**
     * Initializes UI bindings and background styling after FXML loads.
     */
    @FXML
    public void initialize() {
        // Auto-scrolls perfectly to the bottom
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Add The Village background
        dialogContainer.setStyle(
                "-fx-background-image: url('/images/village.png'); " +
                        "-fx-background-size: cover; "
        );

        dialogContainer.minHeightProperty().bind(scrollPane.heightProperty());
    }

    /**
     * Injects the main chat logic instance and shows the welcome message.
     *
     * @param d The chatbox logic instance.
     */
    public void setZhengjun(ZhengjunChatbox d) {
        chatbox = d;
        dialogContainer.getChildren().add(
                DialogBox.getZhengjunDialog(chatbox.getWelcome(), zhengjunImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chatbox.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getZhengjunDialog(response, zhengjunImage)
        );
        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
