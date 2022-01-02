package org.hust.views.home;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hust.common.exception.AlreadyRentBikeException;
import org.hust.controller.HomeController;
import org.hust.controller.RentBikeController;
import org.hust.controller.ViewBikeController;
import org.hust.controller.ViewStationController;
import org.hust.entity.bike.Bike;
import org.hust.entity.bike.EBike;
import org.hust.entity.station.Station;
import org.hust.utils.Utils;
import org.hust.views.BaseScreenHandler;
import org.hust.views.popup.PopupScreen;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class HomeScreenHandler extends BaseScreenHandler implements Initializable {

    @FXML
    TextField searchTextField;

    @FXML
    Button searchButton;

    @FXML
    ScrollPane infoScrollPane;

    @FXML
    Label smallTextLabel;

    @FXML
    ImageView image;

    @FXML
    Button primaryButton;

    @FXML
    Button secondaryButton;

    @FXML
    VBox nowButton;

    /**
     * Save the station List, either by initial or filtered with user search,
     * used as a cache memory for runtime utility purpose
     */
    List<Station> stationList;

    /**
     * Save the station selected by user from the list
     * used as a cache memory for runtime utility purpose
     */
    Station selectedStation;

    /**
     * Save the bike selected by user from the list
     * used as a cache memory for runtime utility purpose
     */
    Bike selectedBike;

    RentBikeController rentBikeController = new RentBikeController(this);


    public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        stationList = ViewStationController.getInstance().listStation();
        setViewStationList();

        searchButton.setOnMouseClicked(mouseEvent -> {
            String location = searchTextField.getText();
            if(StringUtils.isEmpty(location)) {
                stationList = ViewStationController.getInstance().listStation();
            } else {
                stationList = ViewStationController.getInstance().searchStationByLocation(location);
            }
            setViewStationList();
        });

        nowButton.setOnMouseClicked(mouseEvent -> {
            selectedBike = ViewBikeController.getInstance().checkUserRentedBike();
            setViewCurrentBike();
        });

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

    private void setViewBike(){
        smallTextLabel.setText(selectedBike.toString());

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                new Label("Status: " + selectedBike.isStatus()),
                new Label("Speed: " + selectedBike.getSpeed()),
                new Label("Color: " + selectedBike.getColor()),
                new Label("Weight: " + selectedBike.getWeight()),
                new Label("Description: " + selectedBike.getDescription()),
                new Label("Value: " + selectedBike. getValue()));
        if(selectedBike instanceof EBike){
            vBox.getChildren().addAll(
                    new Label("Battery: " + ((EBike) selectedBike).getBattery()),
                    new Label("UsageTime: " + ((EBike) selectedBike).getUsageTime())
            );
        }
        infoScrollPane.setContent(vBox);

        image.setImage(Utils.getImageFromUrl(selectedBike.getImgUrl()));
        System.out.println(selectedBike.getImgUrl());

        primaryButton.setVisible(true);
        primaryButton.setText("Rent this");
        primaryButton.setOnMouseClicked(mouseEvent -> {
            try {
                rentBikeController.rentBike(selectedBike);
            } catch (AlreadyRentBikeException e) {
                try {
                    PopupScreen.error(e.getMessage());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        secondaryButton.setText("Back");
        secondaryButton.setVisible(true);
        secondaryButton.setOnMouseClicked(mouseEvent -> setViewStation());

    }

    private void setViewCurrentBike(){
        smallTextLabel.setText(selectedBike.toString());

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                new Label("Status: " + selectedBike.isStatus()),
                new Label("Speed: " + selectedBike.getSpeed()),
                new Label("Color: " + selectedBike.getColor()),
                new Label("Weight: " + selectedBike.getWeight()),
                new Label("Description: " + selectedBike.getDescription()),
                new Label("Value: " + selectedBike. getValue()),
                new Label("Rent time: " + TimeUnit.MILLISECONDS.toMinutes(selectedBike.getRentTime()) + " minutes"),
                new Label("Current fee: " + 10));
        if(selectedBike instanceof EBike){
            vBox.getChildren().addAll(
                    new Label("Battery: " + ((EBike) selectedBike).getBattery()),
                    new Label("UsageTime: " + ((EBike) selectedBike).getUsageTime())
            );
        }
        infoScrollPane.setContent(vBox);

        image.setImage(Utils.getImageFromUrl(selectedBike.getImgUrl()));
        System.out.println(selectedBike.getImgUrl());

        primaryButton.setVisible(false);

        secondaryButton.setText("Back");
        secondaryButton.setVisible(true);
        secondaryButton.setOnMouseClicked(mouseEvent -> setViewStation());

    }

    private void setViewStation() {
        smallTextLabel.setText(selectedStation.toString());

        VBox vBox = new VBox();
        for(Bike bike : selectedStation.stationBikes()){
            Button button = new Button(bike.toString());
            button.setPrefWidth(infoScrollPane.getWidth());
            button.setOnMouseClicked(mouseClick -> selectedBike = bike);
            vBox.getChildren().add(button);
        }
        infoScrollPane.setContent(vBox);

        image.setImage(Utils.getImageFromUrl(Objects.requireNonNull(getClass().getResource("/img/map.png")).toString()));

        primaryButton.setText("View Bike");
        primaryButton.setVisible(true);
        primaryButton.setOnMouseClicked(mouseEvent -> setViewBike());
        secondaryButton.setText("Back");
        secondaryButton.setVisible(true);
        secondaryButton.setOnMouseClicked(mouseEvent -> {
            if(stationList.size() != 0){
                setViewStationList();
            }
        });
    }

    private void setViewStationList() {
        smallTextLabel.setText("");

        VBox vBox = new VBox();
        for(Station station : stationList){
            Button button = new Button(station.toString());
            button.setPrefWidth(infoScrollPane.getPrefWidth());

            button.setOnMouseClicked(mouseClick -> selectedStation = station);
            vBox.getChildren().add(button);
        }
        infoScrollPane.setContent(vBox);

        image.setImage(Utils.getImageFromUrl(Objects.requireNonNull(getClass().getResource("/img/map.png")).toString()));


        primaryButton.setText("View Station");
        primaryButton.setVisible(true);
        primaryButton.setOnMouseClicked(mouseEvent -> setViewStation());
        secondaryButton.setVisible(false);
    }

}
