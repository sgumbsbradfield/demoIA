module com.example.demoia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires jbcrypt;


    opens com.example.demoia to javafx.fxml;
    exports com.example.demoia;
}