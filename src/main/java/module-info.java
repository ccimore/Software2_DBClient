module main.dbclientappv5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.c195 to javafx.fxml;
    exports com.c195;
    exports com.c195.controller;
    opens com.c195.controller to javafx.fxml;
}