module com.example.demoia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.demoia to javafx.fxml;
    exports com.example.demoia;
}