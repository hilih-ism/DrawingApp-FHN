package com.example.advprogassig;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DrawApp extends Application {
    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DrawApp.class.getResource("/login.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Doodle Labs");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public static void sceneFactory(String fxml) throws IOException{
        scene.setRoot(loadFXML(fxml));
        scene.getWindow().sizeToScene();

    }
    private static Parent loadFXML(String fxml) throws IOException {

        return FXMLLoader.load(DrawApp.class.getResource(fxml+".fxml"));
    }

}