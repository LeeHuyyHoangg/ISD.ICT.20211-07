package org.hust.views.home;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.hust.controller.HomeController;
import org.hust.utils.Utils;
import org.hust.views.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class HomeScreenHandler extends BaseScreenHandler implements Initializable {
    public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());

    public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    @Override
    public HomeController getBController() {
        return (HomeController) super.getBController();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBController(new HomeController());
    }
}
