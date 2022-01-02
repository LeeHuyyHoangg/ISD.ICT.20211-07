package org.hust.views.rentbike;

import java.io.IOException;

import org.hust.common.exception.AlreadyRentBikeException;
import org.hust.controller.RentBikeController;
import org.hust.entity.bike.Bike;
import org.hust.utils.Utils;
import org.hust.views.BaseScreenHandler;
import org.hust.views.popup.PopupScreen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RentBikeConfirmation extends BaseScreenHandler {

  @FXML
  private Label confirmLabel;
  
  @FXML
  private Label titleLabel;
  
  @FXML
  private Label subtitleLabel;
  
  @FXML
  private Label smallTextLabel;

  @FXML
  private Button primaryButton;
  
  @FXML
  private Button secondaryButton;
  
  @FXML
  private ScrollPane infoScrollPane;
  
  @FXML
  private ImageView image;

  private Bike currentlyShowBike;
  
  public RentBikeConfirmation(Stage stage, String screenPath) throws IOException {
    super(stage, screenPath);
    confirmLabel.setText("Renting Confirmation");
    primaryButton.setOnAction(event -> {
      this.stage.close();
      confirmToRentBike();
    });
    secondaryButton.setOnAction(event -> {
      this.stage.close();
    });
  }
  
  public void show(Bike bike) {
    this.currentlyShowBike = bike;
    titleLabel.setText(bike.getModel());
    subtitleLabel.setText(bike.getLocation());
    smallTextLabel.setText(bike.getType());
    super.setImage(image, bike.getImageUrl());
    VBox vb = new VBox();
    vb.getChildren().add(new Label("Speed: " + bike.getSpeed()));
    vb.getChildren().add(new Label("Color: " + bike.getColor()));
    vb.getChildren().add(new Label("Weight: " + bike.getWeight()));
    int deposit = bike.getValue() / 100 * 40;
    vb.getChildren().add(new Label("Deposit: " + Utils.getCurrencyFormat(deposit)));
    infoScrollPane.setContent(vb);
    show();
  }
  
  private void confirmToRentBike() {
    RentBikeController controller = (RentBikeController) getBController();
    try {
      controller.rentBike(currentlyShowBike);
    } catch (AlreadyRentBikeException e) {
      try {
        PopupScreen.error(e.getMessage());
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
  
}
