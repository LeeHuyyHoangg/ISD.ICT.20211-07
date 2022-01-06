package org.hust.views.menu;

import javafx.stage.Stage;
import org.hust.views.BaseScreenHandler;

import java.io.IOException;

public class PricingPolicyPopup extends BaseScreenHandler {
    public PricingPolicyPopup(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        primaryButton.setOnMouseClicked(mouseEvent -> stage.close());
    }
}
