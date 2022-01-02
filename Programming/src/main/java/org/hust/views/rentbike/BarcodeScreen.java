package org.hust.views.rentbike;

import java.io.IOException;

import javafx.scene.layout.VBox;
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
  private Button primaryButton;
  
  @FXML
  private Button secondaryButton;

  @FXML
  private VBox nowButton;
  
  public BarcodeScreen(Stage stage, String screenPath) throws IOException {
    super(stage, screenPath);
    setBController(new RentBikeController(this));
    primaryButton.setOnAction(event -> {
      submitBarcode();
    });
    secondaryButton.setOnAction(event -> {
      getPreviousScreen().show();
    });
    nowButton.setOnMouseClicked(event -> {
      getHomeScreenHandler().setViewCurrentBikeInUse();
      getHomeScreenHandler().show();
    });
    barcodeTextField.setText("01234567");
  }
  
  public void submitBarcode() {
    try {
      RentBikeController controller = (RentBikeController) getBController();
      controller.requestToRentBike(barcodeTextField.getText());
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
