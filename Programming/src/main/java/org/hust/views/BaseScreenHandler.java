package org.hust.views;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hust.controller.BaseController;
import org.hust.views.home.HomeScreenHandler;

import java.io.IOException;
import java.util.Hashtable;

public class BaseScreenHandler extends FXMLScreenHandler {

    protected final Stage stage;
    protected HomeScreenHandler homeScreenHandler;
    protected Hashtable<String, String> messages;
    @FXML
    protected TextField searchTextField;
    @FXML
    protected Button searchButton;
    @FXML
    protected VBox nowButton;
    @FXML
    protected VBox scanButton;
    @FXML
    protected VBox priceButton;
    @FXML
    protected VBox historyButton;
    @FXML
    protected Button primaryButton;
    @FXML
    protected Button secondaryButton;
    @FXML
    protected Label titleLabel;
    @FXML
    protected Label subtitleLabel;
    private Scene scene;
    private BaseScreenHandler prev;
    private BaseController bController;

    private BaseScreenHandler(String screenPath) throws IOException {
        super(screenPath);
        this.stage = new Stage();
    }

    public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
        super(screenPath);
        this.stage = stage;
    }

    public BaseScreenHandler getPreviousScreen() {
        return this.prev;
    }

    public void setPreviousScreen(BaseScreenHandler prev) {
        this.prev = prev;
    }

    public void show() {
        if (this.scene == null) {
            this.scene = new Scene(this.content);
        }
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void setScreenTitle(String string) {
        this.stage.setTitle(string);
    }

    public BaseController getBController() {
        return this.bController;
    }

    public void setBController(BaseController bController) {
        this.bController = bController;
    }

    public void forward(Hashtable messages) {
        this.messages = messages;
    }

    public Stage getStage() {
        return this.stage;
    }

    public HomeScreenHandler getHomeScreenHandler() {
        return this.homeScreenHandler;
    }

    public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
        this.homeScreenHandler = HomeScreenHandler;
    }
}

