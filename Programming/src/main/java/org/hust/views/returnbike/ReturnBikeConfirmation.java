package org.hust.views.returnbike;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hust.controller.ReturnBikeController;
import org.hust.entity.bike.Bike;
import org.hust.entity.bike.EBike;
import org.hust.utils.Utils;
import org.hust.views.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReturnBikeConfirmation extends BaseScreenHandler implements Initializable {
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

    public ReturnBikeConfirmation(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        confirmLabel.setText("Renting Confirmation");
        primaryButton.setOnAction(event -> {
            this.stage.close();
            confirmToReturnBike();
        });
        secondaryButton.setOnAction(event -> this.stage.close());
    }

    public void renderContent(Bike bike) {
        this.currentlyShowBike = bike;
        titleLabel.setText(bike.getModel());
        subtitleLabel.setText(bike.getLocation());
        smallTextLabel.setText(bike.getBikeType());
        image.setImage(new Image(bike.getImgUrl()));
        VBox vb = new VBox();
        vb.getChildren().add(new Label("Usage Time: " + bike.getUsageTime()));
        vb.getChildren().add(new Label("Usage Fees: " + bike.getFee()));
        if (bike instanceof EBike) {
            EBike eBike = (EBike) bike;
            vb.getChildren().add(new Label("Battery: " + eBike.getBattery()));
        }
        int deposit = bike.getValue() / 100 * 40;
        vb.getChildren().add(new Label("Deposit: " + Utils.getCurrencyFormat(deposit)));
        infoScrollPane.setContent(vb);
    }

    private void confirmToReturnBike() {
        ReturnBikeController controller = (ReturnBikeController) getBController();
        controller.returnBike(currentlyShowBike);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
