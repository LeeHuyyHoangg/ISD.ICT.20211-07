package org.hust.views.payment;


import java.io.IOException;

import org.hust.entity.payment.PaymentTransaction;
import org.hust.utils.Configs;
import org.hust.utils.Utils;
import org.hust.views.BaseScreenHandler;
import org.hust.views.rentbike.BarcodeScreen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class handle the GUI for Payment Result Screen.
 *
 */
public class PaymentResultScreenHandler extends BaseScreenHandler {
  
  @FXML
  private Label subtitleLabel;
  
  @FXML
  private Label transactionIdLabel;
  
  @FXML
  private Label cardHolderLabel;
  
  @FXML
  private Label transactionAmountLabel;
  
  @FXML
  private Label transactionContentsLabel;
  
  @FXML
  private Label transactionTimeLabel;
  
  @FXML
  private Button primaryButton;
  
  @FXML
  private Button secondaryButton;
  
  @FXML
  private VBox scanButton;

  public PaymentResultScreenHandler(Stage stage, String screenPath) throws IOException {
    super(stage, screenPath);
    primaryButton.setOnAction(event -> {
      homeScreenHandler.show();
    });
    secondaryButton.setOnAction(event -> {
      homeScreenHandler.show();
    });
    scanButton.setOnMouseClicked(event -> {
      requestToScanBarcode();
    });
  }

  @Override
  public void show() {
    super.show();
  }

  /**
   * Show the result of a successful payment transaction with infos.

   * @param transaction - transaction to be show
   */
  public void show(PaymentTransaction transaction) {
    subtitleLabel.setText("Transaction success!");
    transactionIdLabel.setText(transaction.getId());
    cardHolderLabel.setText(transaction.getOwner());
    transactionAmountLabel.setText(Utils.getCurrencyFormat(transaction.getAmount()));
    transactionContentsLabel.setText(transaction.getContent());
    transactionTimeLabel.setText(transaction.getTime());
    show();
  }
  
  public void showError(Exception e) {
    subtitleLabel.setText("Transaction fail!");
    show();
  }

  private void requestToScanBarcode() {
    try {
      BarcodeScreen barcodeScreen = new BarcodeScreen(this.stage, Configs.BARCODE_PATH);
      barcodeScreen.setHomeScreenHandler(homeScreenHandler);
      barcodeScreen.setPreviousScreen(this);
      barcodeScreen.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
