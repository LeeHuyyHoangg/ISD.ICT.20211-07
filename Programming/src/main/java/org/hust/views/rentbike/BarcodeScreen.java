package org.hust.views.rentbike;

import java.io.IOException;

import org.hust.common.exception.InvalidBarcodeException;
import org.hust.controller.RentBikeController;
import org.hust.views.BaseScreenHandler;
import org.hust.views.popup.PopupScreen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BarcodeScreen extends BaseScreenHandler {
  
  @FXML
  private TextField barcodeTextField;
  
  @FXML
  private Button submitButton;
  
  @FXML
  private Button backButton;
  
  public BarcodeScreen(Stage stage, String screenPath) throws IOException {
    super(stage, screenPath);
    submitButton.setOnAction(event -> {
      submitBarcode();
    });
    backButton.setOnAction(event -> {
      getPreviousScreen().show();
    });
  }
  
  public void submitBarcode() {
    try {
      RentBikeController controller = (RentBikeController) getBController();
      controller.rentBike(barcodeTextField.getText());
    } catch (InvalidBarcodeException e) {
      try {
        PopupScreen.error(e.getMessage());
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }
  
  @Override
  public void show() {
    super.show();
  }
}
