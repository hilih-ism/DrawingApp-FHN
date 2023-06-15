package com.example.advprogassig;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

import static com.example.advprogassig.DrawApp.scene;

public class Register {
    @FXML
    private Button btnlogin2;
    private Alert alert;
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/LoginApp";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    static final String input = "INSERT INTO customer (`Full Name`,`Email`,`Username`,`Password`) VALUES (?, ? ,?, ?)";

    public static void insertData(Scene scene) throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        TextField Fnametxt = (TextField) scene.lookup("#Fname");
        String inputVal = Fnametxt.getText();
        TextField emailtxt = (TextField) scene.lookup("#email");
        String inputVal2 = emailtxt.getText();
        TextField usertxt = (TextField) scene.lookup("#user");
        String inputVal3 = usertxt.getText();
        TextField passtxt = (TextField) scene.lookup("#pass");
        String inputVal4 = passtxt.getText();

        PreparedStatement statement = con.prepareStatement(input);
        statement.setString(1, inputVal);
        statement.setString(2, inputVal2);
        statement.setString(3, inputVal3);
        statement.setString(4, inputVal4);
        statement.executeUpdate();

        con.close();
    }

    public void registerButtonClicked(ActionEvent event) {
        Button registerButton = (Button) event.getSource();
        Scene scene = registerButton.getScene();

        try {
            insertData(scene);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Registration");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Registered");
            alert.showAndWait();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    public void setBtnlogin2(ActionEvent event){
        if(event.getSource().equals(btnlogin2)){
            try {
                DrawApp.sceneFactory("/login");
                scene.getWindow().sizeToScene();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

