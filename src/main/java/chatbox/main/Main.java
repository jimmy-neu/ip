package chatbox.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * A GUI for  using FXML.
 */
public class Main extends Application {
    private ZhengjunChatbox chatbox = new ZhengjunChatbox();

    /**
     * Starts the JavaFX application and shows the main window.
     *
     * @param stage The primary stage provided by JavaFX.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Add this line to set the name in the title bar!
            stage.setTitle("Instructor Iruka");

            fxmlLoader.<MainWindow>getController().setZhengjun(chatbox);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
