package org.hust.views.rentbike;

import java.io.IOException;

import org.hust.controller.RentBikeController;
import org.hust.entity.bike.Bike;
import org.hust.views.BaseScreenHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
    subtitleLabel.setText("Location");
    smallTextLabel.setText(bike.getType());
    VBox vb = new VBox();
    vb.getChildren().add(new Label("Battery: " + bike.getBattery()));
    vb.getChildren().add(new Label("Usage Time: " + bike.getUsageTime()));
    vb.getChildren().add(new Label("Speed: " + bike.getSpeed()));
    vb.getChildren().add(new Label("Color: " + bike.getColor()));
    vb.getChildren().add(new Label("Weight: " + bike.getWeight()));
    int deposit = bike.getValue() / 100 * 40;
    vb.getChildren().add(new Label("Deposit: " + deposit));
    infoScrollPane.setContent(vb);
    show();
  }
  
  public void confirmToRentBike() {
    RentBikeController controller = (RentBikeController) getBController();
    controller.rentBike(currentlyShowBike);
  }
  
  @Override
  public void show() {
    super.show();
  }
}
