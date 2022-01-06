package org.hust.views.payment;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hust.entity.payment.PaymentTransaction;
import org.hust.utils.Configs;
import org.hust.utils.Utils;
import org.hust.views.BaseScreenHandler;
import org.hust.views.rentbike.BarcodeScreen;

import java.io.IOException;

/**
 * This class handle the GUI for Payment Result Screen.
 */
public class PaymentResultScreenHandler extends BaseScreenHandler {
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

    /**
     * Initialize PaymentResultScreenHandler.
     *
     * @param stage      - stage to show the GUI
     * @param screenPath - path to GUI's FXML file
     */
    public PaymentResultScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        primaryButton.setOnAction(event -> {
            getHomeScreenHandler().setViewStationList();
            getHomeScreenHandler().show();
        });
        secondaryButton.setVisible(false);
        scanButton.setOnMouseClicked(event -> {
            requestToScanBarcode();
        });
        nowButton.setOnMouseClicked(event -> {
            getHomeScreenHandler().setViewCurrentBikeInUse();
            getHomeScreenHandler().show();
        });
    }

    @Override
    public void show() {
        super.show();
    }

    /**
     * Show the result of a successful payment transaction with info.
     *
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

    /**
     * Show the result of a fail payment transaction.
     */
    public void showError() {
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
