package org.hust.views.rentbike;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hust.common.exception.InvalidBarcodeException;
import org.hust.controller.RentBikeController;
import org.hust.views.BaseScreenHandler;
import org.hust.views.popup.PopupScreen;

import java.io.IOException;

public class BarcodeScreen extends BaseScreenHandler {

    @FXML
    private TextField barcodeTextField;

    public BarcodeScreen(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        setBController(new RentBikeController(this));
        primaryButton.setOnAction(event -> submitBarcode());
        secondaryButton.setOnAction(event -> getPreviousScreen().show());
        nowButton.setOnMouseClicked(event -> {
            getHomeScreenHandler().setViewCurrentBikeInUse();
            getHomeScreenHandler().show();
        });
        barcodeTextField.setPromptText("01234567");
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
