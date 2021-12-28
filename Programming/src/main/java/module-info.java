module Programming {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.hust to javafx.fxml;
    exports org.hust;
}