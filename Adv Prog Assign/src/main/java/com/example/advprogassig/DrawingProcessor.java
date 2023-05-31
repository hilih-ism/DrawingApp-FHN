package com.example.advprogassig;
//import com.example.advprogassig.HelloApplication;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

public class DrawingProcessor {

    Color color = null; //Default color
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    //    public DrawingProcessor(Group group){
//
//    }
    public DrawingProcessor() {

    }

    private void clearCanvas(GraphicsContext gc, Canvas canvas) {
        gc.setFill(Color.rgb(242, 242, 242));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
        gc.setLineDashes(0);
        // gc.strokeText("X: " + e.getX(), canvas.getWidth() - 200,
        // canvas.getHeight() - 50);
        // gc.strokeText("Y: " + e.getY(), canvas.getWidth() - 200,
        // canvas.getHeight() - 30);
    }

    public  void setColor(Color colorPickerColor) {
        this.color = colorPickerColor;
    }

    //Draw rectangle function
    public void drawRectangle(Group group, Canvas canvas, Color colorPickerColor, boolean square) {
        Rectangle rectangle = new Rectangle();
        rectangle.setX(Math.random() * canvas.getWidth() - 50);
        rectangle.setY(Math.random() * canvas.getWidth() - 36);
        if (square) {
            rectangle.setWidth(100);
            rectangle.setHeight(100);
        } else {
            rectangle.setWidth(100);
            rectangle.setHeight(150);
        }
//        Rectangle rectangle = new Rectangle(x,y,100,100);
//        rectangle.setX(x);
//        rectangle.setY(y);

//        rectangle.setFill(colorPickerColor);
//        rectangle.setStroke(colorPickerColor);

        if (this.color == null) {
            rectangle.setFill(Color.WHITE);
        } else {
            rectangle.setFill(this.color);
        }
        rectangle.setStroke(Color.BLACK);

        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.getTransforms().add(new Rotate(45,0,0));
            }
        });

        rectangle.setOnMousePressed(moveOnMousePressedEventHandler);
        rectangle.setOnMouseDragged(moveOnMouseDraggedEventHandler);

        group.getChildren().add(rectangle);
    }
    public  void drawLine(Group group, Canvas canvas) {
        Line line = new Line(Math.random() * canvas.getWidth() - 50,Math.random() * canvas.getHeight() - 50,Math.random() * canvas.getWidth() - 50,Math.random() * canvas.getHeight() - 50);

        if (this.color != null) {
            line.setStroke(this.color);
        }

        line.setOnMousePressed(moveOnMousePressedEventHandler);
        line.setOnMouseDragged(moveOnMouseDraggedEventHandler);
        group.getChildren().add(line);
    }

    public  void drawEllipse(Group group, Canvas canvas) {
        Ellipse ellipse = new Ellipse(100,100,50,80);
        ellipse.setLayoutY(Math.random() * canvas.getWidth() - 50);
        ellipse.setLayoutX(Math.random() * canvas.getWidth() - 36);

        if (this.color == null) {
            ellipse.setFill(Color.WHITE);
        } else {
            ellipse.setFill(this.color);
        }

        ellipse.setStroke(Color.BLACK);

        ellipse.setOnMousePressed(moveOnMousePressedEventHandler);
        ellipse.setOnMouseDragged(moveOnMouseDraggedEventHandler);

        group.getChildren().add(ellipse);
    }

    public void drawPrimitive(Group group, Canvas canvas) {
        Polygon polygon = new Polygon();

        polygon.getPoints().addAll(
                100d,100d,
                200d,100d,
                300d,100d,
                300d,200d,
                100d,200d,
                200d,150d,
                100d,100d,
                100d,200d

        );


        polygon.setStroke(Color.BLACK);
        polygon.setFill(Color.WHITE);
        group.getChildren().add(polygon);
    }

    EventHandler<MouseEvent> moveOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            orgSceneX = event.getSceneX();
            orgSceneY = event.getSceneY();
            orgTranslateX = ((Shape)(event.getSource())).getTranslateX();
            orgTranslateY = ((Shape)(event.getSource())).getTranslateY();
        }
    };

    EventHandler<MouseEvent> moveOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            double offsetX = event.getSceneX() - orgSceneX;
            double offsetY = event.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            ((Shape)(event.getSource())).setTranslateX(newTranslateX);
            ((Shape)(event.getSource())).setTranslateY(newTranslateY);
        }
    };

}

