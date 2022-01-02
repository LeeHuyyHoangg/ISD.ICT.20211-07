package org.hust.views.returnbike;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.hust.controller.ReturnBikeController;
import org.hust.views.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReturnBikeScreenHandler extends BaseScreenHandler implements Initializable {
    public ReturnBikeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    @Override
    public ReturnBikeController getBController() {
        return (ReturnBikeController) super.getBController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBController(new ReturnBikeController());
    }

    @Override
    public void show() {
        super.show();
    }
}
