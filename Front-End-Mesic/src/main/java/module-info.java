module com.example.frontendmesic {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.frontendmesic to javafx.fxml;
    exports com.example.frontendmesic;
}