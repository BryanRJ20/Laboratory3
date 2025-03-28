module ucr.laboratory {
    requires javafx.controls;
    requires javafx.fxml;


    opens ucr.laboratory to javafx.fxml;
    exports ucr.laboratory;
    exports controller;
    opens controller to javafx.fxml;
}