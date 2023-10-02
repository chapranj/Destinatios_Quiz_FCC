module com.example.destinationsquiz {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.destinationsquiz to javafx.fxml;
    exports com.example.destinationsquiz;
}