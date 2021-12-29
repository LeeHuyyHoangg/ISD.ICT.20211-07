package org.hust.views.payment;


import java.io.IOException;

import org.hust.common.exception.InvalidFormatException;
import org.hust.controller.TransactionController;
import org.hust.entity.payment.PaymentTransaction;
import org.hust.utils.Configs;
import org.hust.views.BaseScreenHandler;
import org.hust.views.popup.PopupScreen;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This class handle the GUI for Payment Screen.
 *
 */
public class PaymentScreenHandler extends BaseScreenHandler{
  
  private PopupScreen loadingPopup = PopupScreen.loading("Please wait while we are making payment transaction");
  
  private Object key = new Object();
  private boolean isSuccess = true;

  @FXML
  private TextField firstTextField;

  @FXML
  private TextField secondTextField;
  
  @FXML
  private TextField thirdTextField;

  @FXML
  private TextField fifthTextField;
  
  @FXML
  private Button primaryButton;
  
  @FXML
  private Button secondaryButton;
  
  @FXML
  private CheckBox methodCheckBox;

  /**
   * Initialize PaymentScreenHandler.

   * @param stage      - stage to show the GUI
   * @param screenPath - path to GUI's FXML file
   */
  public PaymentScreenHandler(Stage stage, String screenPath) throws IOException {
    super(stage, screenPath);
    stage.setOnCloseRequest(event -> {
      prepareToClose();
    });
    primaryButton.setOnAction(event -> {
      submitTransactionInfo();
    });
    secondaryButton.setOnAction(event -> {
      prepareToClose();
      stage.close();
    });
    methodCheckBox.setSelected(true);
    methodCheckBox.setDisable(true);
  }
  
  /**
   * Request to make a payment transaction.

   * @param amount   - amount of money in the transaction
   * @param contents - the transaction contents
   * @return true   - if the transaction success
   *         false  - if the transaction fail
   */
  public boolean requestToMakeTransaction(int amount, String contents) {
    TransactionController controller = (TransactionController) getBController();
    controller.setTransactionAmount(amount);
    controller.setTransactionContents(contents);
    show();
    Platform.enterNestedEventLoop(key);
    if (isSuccess) {
      return true;
    } else {
      isSuccess = true;
      return false;
    }
  }

  private void submitTransactionInfo() {
    try {
      TransactionController controller = (TransactionController) getBController();
      controller.checkTransactionInfo(firstTextField.getText(), 
    	  secondTextField.getText(), 
    	  fifthTextField.getText(), 
          thirdTextField.getText());
      loadingPopup.show();
      PaymentTransaction transaction = controller.makeTransaction(firstTextField.getText(),
    	  secondTextField.getText(),
    	  fifthTextField.getText(),
    	  thirdTextField.getText());
      loadingPopup.close(0);
      Platform.exitNestedEventLoop(key, null);
      transaction.save();
      stage.setOnCloseRequest(null);
      PaymentResultScreenHandler resultScreen = 
          new PaymentResultScreenHandler(stage, Configs.PAYMENT_RESULT_PATH);
      resultScreen.show(transaction);
    } catch (InvalidFormatException e) {
      try {
        PopupScreen.error(e.getMessage());
      } catch (Exception ex) {
    	ex.printStackTrace();
      }
    } catch (Exception e) {
      try {
        stage.setOnCloseRequest(null);
        PaymentResultScreenHandler resultScreen = 
 	        new PaymentResultScreenHandler(stage, Configs.PAYMENT_RESULT_PATH);
        resultScreen.showError(e);
        isSuccess = false;
        Platform.exitNestedEventLoop(key, null);
        e.printStackTrace();
      } catch (Exception ex) {
    	ex.printStackTrace();
      }
    } finally {
      loadingPopup.close(0);
    }
  }
  
  private void prepareToClose() {
    if (this instanceof PaymentScreenHandler) {
      isSuccess = false;
      Platform.exitNestedEventLoop(key, null);
    }
  }
  
  @Override
  public void show() {
    super.show();
  }
}
