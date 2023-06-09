package com.example.advprogassig;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DrawController implements Initializable {

    @FXML
    private Button rectanglebtn;

    @FXML
    private Button trianglebtn;

    @FXML
    private Button ellipsebtn;

    @FXML
    private Button circlebtn;


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
    private Canvas canvas;

    boolean selectTool=false;
    boolean drawRectangle=false;
    boolean drawCircle=false;
    boolean drawTriangle=false;
    boolean drawEllipse=false;
    GraphicsContext linebtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        linebtn= canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e->{
            double size=Double.parseDouble(thicknes.getText());
            double x=e.getX()-size/2;
            double y=e.getY()-size/2;
            if(selectTool && !thicknes.getText().isEmpty()){
                linebtn.setFill(colorPicker.getValue());
                linebtn.fillRoundRect(x,y,size,size,size,size);
            }

        });
        //linebtn = canvas.getGraphicsContext2D();

        canvas.setOnMouseClicked(e -> {
            if (drawRectangle && !widthfield.getText().isEmpty() && !heightfield.getText().isEmpty()) {
                double width = Double.parseDouble(widthfield.getText());
                double height = Double.parseDouble(heightfield.getText());
                double x = e.getX() - width / 2;
                double y = e.getY() - height / 2;

                linebtn.setFill(colorPicker.getValue());
                linebtn.fillRect(x, y, width, height);
            }
        });

// to draw circle
        canvas.setOnMouseClicked(e -> {
            if (drawCircle && !radiusfield.getText().isEmpty()) {
                double radius = Double.parseDouble(radiusfield.getText());
                double x = e.getX() - radius;
                double y = e.getY() - radius;

                linebtn.setFill(colorPicker.getValue());
                linebtn.fillOval(x, y, radius * 2, radius * 2);
            }
        });
        // to draw triangle

        canvas.setOnMouseClicked(e -> {
            if ( drawTriangle && !widthfield.getText().isEmpty() && !heightfield.getText().isEmpty()) {
                double width = Double.parseDouble(widthfield.getText());
                double height = Double.parseDouble(heightfield.getText());
                double x = e.getX() - width / 2;
                double y = e.getY() - height / 2;

                linebtn.setFill(colorPicker.getValue());
                linebtn.fillPolygon(new double[]{x, x + width, x + width / 2}, new double[]{y + height, y + height, y}, 3);
            }
        });
        // to draw ellipse

        canvas.setOnMouseClicked(e -> {
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
    public void newcanvas(ActionEvent e){
        TextField canvaswidth=new TextField();
        canvaswidth.setPromptText("Width");
        canvaswidth.setPrefWidth(150);
        canvaswidth.setAlignment(Pos.CENTER);

        TextField canvasheight=new TextField();
        canvasheight.setPromptText("Height");
        canvasheight.setPrefWidth(150);
        canvasheight.setAlignment(Pos.CENTER);

        Button createbtn=new Button();
        createbtn.setText("create canvas");

        VBox vBox=new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(canvaswidth,canvasheight,createbtn);

        Stage stage=new Stage();
        AnchorPane pane=new AnchorPane();
        pane.setPrefWidth(400);
        pane.setPrefHeight(400);
        pane.getChildren().addAll(vBox);

        Scene canvascene=new Scene(pane);
        stage.setTitle("Create Canvas");
        stage.setScene(canvascene);
        stage.show();
createbtn.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        double recivedwidth=Double.parseDouble(canvaswidth.getText());
        double reciveheight=Double.parseDouble(canvasheight.getText());
//        canvas=new Canvas();
        canvas.setWidth(recivedwidth);
        canvas.setHeight(reciveheight);
        vBox.getChildren().add(canvas);
        stage.close();
    }
});

    }
    public void SelectTool(ActionEvent e){
        selectTool=true;
    }
    public void drawRectangle(ActionEvent e){
        drawRectangle=true;
    }
    public void drawCircle(ActionEvent e){
        drawCircle=true;
    }
    public void drawTriangle(ActionEvent e){
        drawTriangle=true;
    }
    public void drawEllipse(ActionEvent e){
        drawEllipse=true;
    }
}
