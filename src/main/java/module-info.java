module main.dbclientappv5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens main.dbclientappv5 to javafx.fxml;
    exports main.dbclientappv5;
}