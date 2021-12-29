package org.hust.views.popup;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.hust.utils.Configs;
import org.hust.views.BaseScreenHandler;

import java.io.IOException;


public class PopupScreen extends BaseScreenHandler {
    @FXML
    ImageView image;

    @FXML
    Label message;

    @FXML
    Button primaryButton;


    public PopupScreen(Stage stage) throws IOException {
        super(stage, Configs.POPUP_PATH);
        primaryButton.setOnMouseClicked(mouseEvent -> close(0));
    }

    private static PopupScreen popup(String message, String imagepath, Boolean undecorated) throws IOException {
        PopupScreen popup = new PopupScreen(new Stage());
        if (undecorated) popup.stage.initStyle(StageStyle.UNDECORATED);
        popup.message.setText(message);
        popup.setImage(imagepath);
        return popup;
    }

    public static void success(String message) throws IOException {
        popup(message, Configs.IMAGE_PATH + "/" + "check.png", true).show(true);
    }

    public static void error(String message) throws IOException {
        popup(message, Configs.IMAGE_PATH + "/" + "cancel.png", false).show(false);
    }

    public static PopupScreen loading(String message) throws IOException {
        return popup(message, Configs.IMAGE_PATH + "/" + "loading.gif", true);
    }

    public void setImage(String path) {
        super.setImage(image, path);
    }

    public void show(Boolean autoclose) {
        super.show();
        if (autoclose) close(1);
    }

    public void show(double time) {
        super.show();
        close(time);
    }

    public void close(double time) {
        PauseTransition delay = new PauseTransition(Duration.seconds(time));
        delay.setOnFinished(event -> stage.close());
        delay.play();
    }
}
