package com.example.advprogassig;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DrawController implements Initializable {

    @FXML
    private Button rectanglebtn;

    @FXML
    private Button trianglebtn;

    @FXML
    private Button ellipsebtn;

    @FXML
    private Button circlebtn, brushbtn, erasebtn;


    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField thicknes;


    @FXML
    private Button clearbtn;

    @FXML
    private TextField heightfield;

    @FXML
    private TextField widthfield;

    @FXML
    private TextField radiusfield;
    @FXML
    private Button logoutbtn;

    @FXML
    private Canvas canvas;
    boolean selectTool = false;
    boolean drawRectangle = false;
    boolean drawCircle = false;
    boolean drawTriangle = false;
    boolean drawEllipse = false;
    boolean eraseScene = false;
    double lastX;
    double lastY;
    GraphicsContext linebtn;
    private ArrayList<Node> nodesList = new ArrayList<>();

    public void logout(ActionEvent event) {
        if(event.getSource().equals(logoutbtn)){
            try {
                DrawApp.sceneFactory("/login");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        linebtn = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(thicknes.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
            if (selectTool && !thicknes.getText().isEmpty()) {
                linebtn.setFill(colorPicker.getValue());
                linebtn.fillRoundRect(x, y, size, size, size, size);
            }
            //erase

            if (eraseScene && e.isPrimaryButtonDown()) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(Color.WHITE);
                double eraserSize = Double.parseDouble(thicknes.getText()) * 5;
                gc.fillRect(e.getX() - eraserSize / 2, e.getY() - eraserSize / 2, eraserSize, eraserSize);

                lastX = e.getX();
                lastY = e.getY();

            }
        });
        //draw rectangle

        canvas.setOnMousePressed(e -> {
            if (drawRectangle && !widthfield.getText().isEmpty() && !heightfield.getText().isEmpty()) {
                double width = Double.parseDouble(widthfield.getText());
                double height = Double.parseDouble(heightfield.getText());
                double x = e.getX() - width / 2;
                double y = e.getY() - height / 2;

                linebtn.setFill(colorPicker.getValue());
                linebtn.fillRect(x, y, width, height);
            } else
// to draw circle
                if (drawCircle && !radiusfield.getText().isEmpty()) {
                    double radius = Double.parseDouble(radiusfield.getText());
                    double x = e.getX() - radius;
                    double y = e.getY() - radius;

                    linebtn.setFill(colorPicker.getValue());
                    linebtn.fillOval(x, y, radius * 2, radius * 2);
                } else
                    // to draw triangle
                    if (drawTriangle && !widthfield.getText().isEmpty() && !heightfield.getText().isEmpty()) {
                        double width = Double.parseDouble(widthfield.getText());
                        double height = Double.parseDouble(heightfield.getText());
                        double x = e.getX() - width / 2;
                        double y = e.getY() - height / 2;

                        linebtn.setFill(colorPicker.getValue());
                        linebtn.fillPolygon(new double[]{x, x + width, x + width / 2}, new double[]{y + height, y + height, y}, 3);
                    } else
                        // to draw ellipse
                        if (drawEllipse && !widthfield.getText().isEmpty() && !heightfield.getText().isEmpty()) {
                            double width = Double.parseDouble(widthfield.getText());
                            double height = Double.parseDouble(heightfield.getText());
                            double x = e.getX() - width / 2;
                            double y = e.getY() - height / 2;

                            linebtn.setFill(colorPicker.getValue());
                            linebtn.fillOval(x, y, width, height);
                        }
        });


    }

//    public void newcanvas(ActionEvent e) {
//        TextField canvaswidth = new TextField();
//        canvaswidth.setPromptText("Width");
//        canvaswidth.setPrefWidth(150);
//        canvaswidth.setAlignment(Pos.CENTER);
//
//        TextField canvasheight = new TextField();
//        canvasheight.setPromptText("Height");
//        canvasheight.setPrefWidth(150);
//        canvasheight.setAlignment(Pos.CENTER);
//
//        Button createbtn = new Button();
//        createbtn.setText("create canvas");
//
//        VBox vBox = new VBox();
//        vBox.setSpacing(5);
//        // vBox.paddingProperty(Insets int x=.10);
//        vBox.setAlignment(Pos.CENTER);
//        vBox.getChildren().addAll(canvaswidth, canvasheight, createbtn);
//
//        Stage stage = new Stage();
//        AnchorPane pane = new AnchorPane();
//        pane.setPrefWidth(400);
//        pane.setPrefHeight(400);
//        pane.getChildren().addAll(vBox);
//
//        Scene canvascene = new Scene(pane);
//        stage.setTitle("Create Canvas");
//        stage.setScene(canvascene);
//        stage.show();
//        createbtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event)
//                double recivedwidth = Double.parseDouble(canvaswidth.getText());
//                double reciveheight = Double.parseDouble(canvasheight.getText());
//                Canvas newCanvas = new Canvas(recivedwidth, reciveheight);
//                Pane canvasPane = new Pane(newCanvas);
//                canvasPane.setLayoutX(0);
//                canvasPane.setLayoutY(0);
//                pane.getChildren().add(canvasPane);
//                vBox.getChildren().add(canvas);
//                stage.close();
//            }
//        });
//
//    }
    public void clearscene(ActionEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void SelectTool(ActionEvent e) {
        selectTool = true;
        drawTriangle = false;
        drawCircle = false;
        drawRectangle = false;
        drawEllipse = false;
        eraseScene=false;
    }

    public void drawRectangle(ActionEvent e) {
        drawRectangle = true;
        drawTriangle = false;
        drawCircle = false;
        drawEllipse = false;
        selectTool=false;
        eraseScene=false;
    }

    public void drawCircle(ActionEvent e) {
        drawCircle = true;
        drawTriangle = false;
        drawRectangle = false;
        drawEllipse = false;
        selectTool=false;
        eraseScene=false;
    }

    public void drawTriangle(ActionEvent e) {
        drawTriangle = true;
        drawCircle = false;
        drawRectangle = false;
        drawEllipse = false;
        selectTool=false;
        eraseScene=false;
    }

    public void drawEllipse(ActionEvent e) {
        drawEllipse = true;
        drawTriangle = false;
        drawCircle = false;
        drawRectangle = false;
        eraseScene=false;
        selectTool=false;

    }

    public void eraseScene(ActionEvent e) {
        eraseScene = true;
        drawEllipse = false;
        drawTriangle = false;
        drawCircle = false;
        drawRectangle = false;
        selectTool=false;

    }

   public void save(ActionEvent event) throws IOException {
        // Create a new FileChooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpg", "*.jpeg")
        );

        // Show the dialog and get the selected file
        File file = fileChooser.showSaveDialog(canvas.getScene().getWindow());

        if (file != null) {
            {
// Create a WritableImage object with the same dimensions as the Canvas
                WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());

                // Get the PixelWriter of the WritableImage
                PixelWriter pixelWriter = writableImage.getPixelWriter();

                // Get the canvas graphics context and draw on the WritableImage
                GraphicsContext gc = canvas.getGraphicsContext2D();
                Image snapshot = canvas.snapshot(null, writableImage);
                gc.drawImage(snapshot, 0, 0);

                // Write the image data to the selected file location using the PixelWriter class
                String format = "png";
                if (file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg")) {
                    format = "jpeg";
                }
                //ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), format, file);

                // Show a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Image saved to " + file.getAbsolutePath());
                alert.showAndWait();

            }
        }
    }
    public void saveCanvas(ActionEvent event) {
    }
}
