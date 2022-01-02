module Programming {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires lombok;
    requires mongo.java.driver;
    requires jackson.databind;
    requires jackson.core;
    requires commons.lang3;
    requires reflections;
    requires java.sql;

    opens org.hust to javafx.fxml;
    opens org.hust.views to javafx.fxml;
    opens org.hust.views.home to javafx.fxml;
    opens org.hust.views.payment to javafx.fxml;
    opens org.hust.views.popup to javafx.fxml;
    opens org.hust.views.rentbike to javafx.fxml;
    opens org.hust.entity.station to jackson.databind;
    exports org.hust;
}