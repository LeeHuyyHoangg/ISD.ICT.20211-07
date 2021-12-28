module Programming {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens org.hust to javafx.fxml;
    opens org.hust.views to javafx.fxml;
    exports org.hust;
}