package org.hust.views.payment;


import java.io.IOException;

import org.hust.entity.payment.PaymentTransaction;
import org.hust.utils.Utils;
import org.hust.views.BaseScreenHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This class handle the GUI for Payment Result Screen.
 *
 */
public class PaymentResultScreenHandler extends BaseScreenHandler {
  
  @FXML
  private Label transactionResultLabel;
  
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
  private Button confirmButton;

  public PaymentResultScreenHandler(Stage stage, String screenPath) throws IOException {
    super(stage, screenPath);
    confirmButton.setOnAction(event -> {
      this.stage.close();
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
    transactionResultLabel.setText("Transaction success!");
    transactionIdLabel.setText(transaction.getId());
    cardHolderLabel.setText(transaction.getOwner());
    transactionAmountLabel.setText(Utils.getCurrencyFormat(transaction.getAmount()));
    transactionContentsLabel.setText(transaction.getContent());
    transactionTimeLabel.setText(transaction.getTime());
    show();
  }
  
  public void showError(Exception e) {
    transactionResultLabel.setText("Transaction fail!");
    show();
  }

}
