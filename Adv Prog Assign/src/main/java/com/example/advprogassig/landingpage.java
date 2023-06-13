package com.example.landingpage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class landingpage extends Application {
    private landingPageController landingViewController;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("landingPage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

        public void setLandingViewController (landingPageController controller){
            landingViewController = controller;
        }

        public landingPageController getLandingViewController () {
            return landingViewController;
        }

        public static void main (String[]args){
            launch(args);
        }
    }

