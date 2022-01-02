module Programming {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires mongo.java.driver;
    requires lombok;
    requires jackson.databind;

    opens org.hust to javafx.fxml;
    opens org.hust.views to javafx.fxml;
    opens org.hust.views.home to javafx.fxml;
    opens org.hust.views.payment to javafx.fxml;
    opens org.hust.views.popup to javafx.fxml;
    opens org.hust.views.rentbike to javafx.fxml;
    exports org.hust;
}