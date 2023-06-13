package com.example.landingpage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ListView;

import java.io.IOException;

public class landingPageController {

    @FXML
    private ListView<Canvas> projectListView;

    public void createNewProject() throws IOException {
        Canvas newProject = new Canvas(600, 400);
        projectListView.getItems().add(newProject);

        // Redirect to drawing workspace
        FXMLLoader loader = new FXMLLoader(getClass().getResource("draw.fxml"));
        Parent drawPage = loader.load();
        DrawingController controller = loader.getController();
        controller.setProject(newProject);

        // Replace the root of the scene with the drawing workspace
        Stage stage = (Stage) projectListView.getScene().getWindow();
        stage.getScene().setRoot(drawPage);
    }
}
