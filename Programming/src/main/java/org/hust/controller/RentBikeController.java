package org.hust.controller;

import java.io.IOException;

import org.hust.views.BaseScreenHandler;
import org.hust.common.exception.InvalidBarcodeException;
import org.hust.entity.bike.Bike;
import org.hust.utils.Configs;
import org.hust.views.payment.PaymentScreenHandler;
import org.hust.views.rentbike.RentBikeConfirmation;

import javafx.stage.Stage;

public class RentBikeController extends BaseController {

  private BaseScreenHandler screenThatCallRentBike;
  
  /**
   * For test only, do not use this.
   */
  public RentBikeController() {
    ;
  }
  
  public RentBikeController(BaseScreenHandler screenThatCallRentBike) {
    this.screenThatCallRentBike = screenThatCallRentBike;
  }

  public void requestToRentBike(String barcode) throws InvalidBarcodeException {
    if (!validateBarcode(barcode)) {
      throw new InvalidBarcodeException();
    }
    Bike bike = new Bike();
    bike.getBike(barcode);
    try {
      RentBikeConfirmation bikeScreen = new RentBikeConfirmation(new Stage(), Configs.RENT_BIKE_CONFIRM_PATH);
      bikeScreen.setBController(this);
      bikeScreen.show(bike);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void requestToRentBike(Bike bike) {
    try {
      RentBikeConfirmation bikeScreen = new RentBikeConfirmation(new Stage(), Configs.RENT_BIKE_CONFIRM_PATH);
      bikeScreen.setBController(this);
      bikeScreen.show(bike);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Process to make payment transaction and rent bike, this should only be called from
   * RentBikeConfirmPopup.
   * @param bike - bike to be rent
   */
  public void rentBike(Bike bike) {
    String transactionContents = "Fee for rent bike " + bike.getModel();
    int transactionAmount = bike.getValue() / 100 * 40;
    try {
      PaymentScreenHandler paymentScreen = new PaymentScreenHandler(screenThatCallRentBike.getStage(), Configs.PAYMENT_PATH);
      paymentScreen.setHomeScreenHandler(screenThatCallRentBike.getHomeScreenHandler());
      paymentScreen.setPreviousScreen(screenThatCallRentBike);
      paymentScreen.setBController(new TransactionController());
      if (paymentScreen.requestToMakeTransaction(transactionAmount, transactionContents)) {
        bike.unlock();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public boolean validateBarcode(String barcode) {
    if (barcode == null) {
      return false;
    }
    barcode = barcode.trim();
    if (barcode.length() != 8) {
      return false;
    }
    try {
      Integer.parseInt(barcode);
    } catch (Exception e) {
      return false;
    }
    return true;
  }
}