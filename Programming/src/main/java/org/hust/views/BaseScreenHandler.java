package org.hust.views;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hust.controller.BaseController;
import org.hust.views.home.HomeScreenHandler;

import java.io.IOException;
import java.util.Hashtable;

public class BaseScreenHandler extends FXMLScreenHandler {

    protected final Stage stage;
    protected HomeScreenHandler homeScreenHandler;
    protected Hashtable<String, String> messages;
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

    public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
        this.homeScreenHandler = HomeScreenHandler;
    }
    
    public Stage getStage() {
        return this.stage;
    }
    
    public HomeScreenHandler getHomeScreenHandler() {
        return this.homeScreenHandler;
    }
}

