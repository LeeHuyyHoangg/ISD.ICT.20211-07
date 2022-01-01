package org.hust.views.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import org.hust.controller.HomeController;
import org.hust.utils.Configs;
import org.hust.utils.Utils;
import org.hust.views.BaseScreenHandler;
import org.hust.views.rentbike.BarcodeScreen;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeScreenHandler extends BaseScreenHandler implements Initializable {
    public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());
    
    @FXML
    private Button primaryButton;
    
    @FXML
    private Button secondaryButton;
    
    @FXML
    private VBox scanButton;

    public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

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
        secondaryButton.setOnAction(event -> {
          requestToScanBarcode();
        });
        
        scanButton.setOnMouseClicked(event -> {
          requestToScanBarcode();
        });
    }
    
    private void requestToScanBarcode() {
      try {
        BarcodeScreen barcodeScreen = new BarcodeScreen(this.stage, Configs.BARCODE_PATH);
        barcodeScreen.setHomeScreenHandler(this);
        barcodeScreen.setPreviousScreen(this);
        barcodeScreen.show();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
}
