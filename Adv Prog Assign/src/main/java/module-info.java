module com.example.advprogassig {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.advprogassig to javafx.fxml;
    exports com.example.advprogassig;
}