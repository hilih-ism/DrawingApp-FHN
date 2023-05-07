import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DrawingApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the root node of the scene graph
        StackPane root = new StackPane();
        
        // Create a scene with the root node and set its dimensions
        Scene scene = new Scene(root, 400, 300);
        
        // Set the scene to the primary stage
        primaryStage.setScene(scene);
        
        // Set the title of the window
        primaryStage.setTitle("FHN Drawing App");
        
        // Show the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
