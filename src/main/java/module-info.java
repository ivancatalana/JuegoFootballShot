module com.example.juegofootballshot {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.juegofootballshot to javafx.fxml;
    exports com.example.juegofootballshot;
}