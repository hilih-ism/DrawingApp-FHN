module com.example.advprogassig {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;


    opens com.example.advprogassig to javafx.fxml;
    exports com.example.advprogassig;
}