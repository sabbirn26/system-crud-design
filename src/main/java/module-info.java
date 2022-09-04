module com.example.systemcruddesign {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;


    opens com.example.systemcruddesign to javafx.fxml;
    exports com.example.systemcruddesign;
}