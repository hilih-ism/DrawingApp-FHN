package com.example.advprogassig;

import com.example.advprogassig.DrawingProcessor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {


        double Xcoord = 0;
        double Ycoord = 0;

        Canvas canvas = new Canvas(800,600);

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 800,600);

//        stage.setResizable(false);
        stage.setScene(scene);

        Group nodeGroup = new Group();

        //Buttons
        Button rectangleBtn = new Button("Rectangle", new ImageView("file:img/rectangle.png")); //btn for drawing rectangle
        Button squareBtn = new Button("Square", new ImageView("file:img/square.PNG")); //btn for drawing rectangle
        Button lineBtn = new Button("Line", new ImageView("file:img/line.png")); //btn for drawing Line
        Button ellipseBtn = new Button("Ellipse", new ImageView("file:img/ellipse.png")); //btn for drawing Line

        //Exam
        Button primitiveBtn = new Button("Exam");

        //Input Text Fields for the Coords X & Y -> set them by the mouse
        Label labelX = new Label("X:");
        TextField coordX = new TextField();
//        Xcoord = Double.parseDouble(coordX.toString());

        Label labelY = new Label("Y:");
        TextField coordY = new TextField();
//        Ycoord = Double.parseDouble(coordY.toString());

        // Color Picker
        final ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        final Text cpHint = new Text("Select a color");
        cpHint.setFont(Font.font ("Verdana", 20));
        cpHint.setFill(colorPicker.getValue());
        colorPicker.setTooltip(new Tooltip("Select a color"));


        //Tool Bar - put the buttons and stuff here
        ToolBar toolBar = new ToolBar(rectangleBtn,squareBtn,lineBtn,ellipseBtn,colorPicker,labelX,coordX,labelY,coordY,primitiveBtn);

        //Vertical Toolbar
        VBox vBox = new VBox();
        vBox.getChildren().addAll(toolBar,nodeGroup);

        //Horizontal ToolBar
//        HBox hBox = new HBox();
//        hBox.getChildren().addAll(colorPicker);

        DrawingProcessor drawingProcessor = new DrawingProcessor();

        //Select color
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawingProcessor.setColor(colorPicker.getValue());
            }
        });

        //Actions with the buttons
        rectangleBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                DrawingProcessor.drawRectangle(nodeGroup, canvas, colorPicker.getValue(), Xcoord, Ycoord,false);

                drawingProcessor.drawRectangle(nodeGroup, canvas, colorPicker.getValue(), false);

                for(Node node : nodeGroup.getChildren()) {
                    System.out.println(node.toString());
//                    System.out.println(colorPicker.getValue());
                }
            }
        });

        squareBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawingProcessor.drawRectangle(nodeGroup, canvas, colorPicker.getValue(), true);
            }
        });

        lineBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//
                drawingProcessor.drawLine(nodeGroup,canvas);

                for(Node node : nodeGroup.getChildren()) {
                    System.out.println(node.toString());
                }
            }
        });

        ellipseBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawingProcessor.drawEllipse(nodeGroup, canvas);
            }
        });

        primitiveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawingProcessor.drawPrimitive(nodeGroup, canvas);
            }
        });

        //Taking mouse coords
        borderPane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("end X: " + event.getSceneX());
                System.out.println("end Y: " + event.getSceneY());
            }
        });

        borderPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                coordX.setText("" + event.getX());
                coordY.setText("" + event.getY());
                System.out.println("start X: " + event.getSceneX());
                System.out.println("start Y: " + event.getSceneY());
            }
        });


        borderPane.setTop(vBox);
//        borderPane.setLeft(hBox);

        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}