package chatbox.main;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        // User bubble: Light blue background, rounded corners, padding
        db.dialog.setStyle("-fx-background-color: #EB9154; -fx-background-radius: 15; -fx-padding: 10;");
        return db;
    }

    public static DialogBox getZhengjunDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();

        // Bot bubble: Differentiate normal messages from errors
        if (text.startsWith("Nani?!")) {
            // Error bubble: Light red background with dark red text
            db.dialog.setStyle("-fx-background-color: #FFCCCC; -fx-text-fill: #900000; -fx-background-radius: 15; -fx-padding: 10;");
        } else {
            // Normal bot bubble: Light gray background
            db.dialog.setStyle("-fx-background-color: #54EBE3; -fx-text-fill: #000000; -fx-background-radius: 15; -fx-padding: 10;");
        }
        return db;
    }
}