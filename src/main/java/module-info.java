module com.example.figurprogram {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.figurprogram to javafx.fxml;
    exports com.example.figurprogram;
    exports com.example.figurprogram.Figur;
    opens com.example.figurprogram.Figur to javafx.fxml;
}