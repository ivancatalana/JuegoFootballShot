package com.example.juegofootballshot;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MillDrawingExample extends Application {
    //Animation Variables ( Abnimaciones del Sprite Jugador)
    Timeline animation ;
    int NUM_SPRITES = 10;
    int index = 0;
    double posX = 480;
    double posY = 800;
    Image imageSprite = new Image("messiSprite2.png", false);
    double width = imageSprite.getWidth();
    double height = imageSprite.getHeight();
    boolean disparo;
    @Override
    public void start(Stage stage) {
        int index;
        // create a group to hold the shapes
        Group root = new Group();
        Canvas canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // create the body of the mill
        Rectangle body = new Rectangle();
        body.setX(100.0f);
        body.setY(300.0f);
        body.setWidth(30);
        body.setHeight(75);
        body.setFill(Color.BLACK);
        body.setStroke(Color.BLACK);

        // create the sails of the mill
        Rectangle sail1 = new Rectangle();
        sail1.setX(75);
        sail1.setY(300.0f);
        sail1.setWidth(80.0f);
        sail1.setHeight(5.0f);
        sail1.setFill(Color.BLACK);
        sail1.setStroke(Color.BLACK);
// create a group for the circles
        Group circles = new Group();

// create two circles
        Circle circle1 = new Circle(75, 300, 12);
        Circle circle2 = new Circle(155, 300, 12);

// set the fill color for the circles
        circle1.setFill(new ImagePattern(new Image("bullseye.png")));
        circle2.setFill(new ImagePattern(new Image("bullseye.png")));

// add the circles to the group
        circles.getChildren().add(circle1);
        //circles.getChildren().add(sail1);
        circles.getChildren().add(circle2);

// add the group to the root

        RotateTransition rotate2 = new RotateTransition(Duration.seconds(4),sail1);
        rotate2.setByAngle(360);
        rotate2.setCycleCount(Timeline.INDEFINITE);
        rotate2.setAutoReverse(false);


        // add the shapes to the group
        root.getChildren().add(body);
        root.getChildren().add(circles);
        root.getChildren().add(sail1);

        // create the rotation animation for the sails
        RotateTransition rotate1 = new RotateTransition(Duration.seconds(4),circles);
        rotate1.setByAngle(360);
        rotate1.setCycleCount(Timeline.INDEFINITE);
        rotate1.setAutoReverse(false);


        // create the translation animation for the mill
        TranslateTransition translate = new TranslateTransition(Duration.seconds(15), root);
        translate.setFromX(900);
        translate.setToX(-200);
        translate.setCycleCount(Timeline.INDEFINITE);
        // add the shapes to the group


        // start the rotation and translation animations
        rotate1.play();
        rotate2.play();

        translate.play();

        // create the scene
        Scene scene = new Scene(root, 900, 600);

        // set the title for the stage and show it
        stage.setTitle("Mill Animation Example");
        stage.setScene(scene);
        stage.show();
    }
    public void showSprite(GraphicsContext gc) {
        if(animation!=null) animation.stop();
        KeyFrame frame = new KeyFrame(Duration.millis(150), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // gc.clearRect(0, 0, 900, 600);

                gc.drawImage(imageSprite, (index % NUM_SPRITES) * (width / NUM_SPRITES), 0, (width / NUM_SPRITES), height, posX, posY, (width / NUM_SPRITES), height);
                index++;
                if (index == NUM_SPRITES) {
                    index = 0;
                    animation.stop();
                }
            }
        });
        animation=new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
