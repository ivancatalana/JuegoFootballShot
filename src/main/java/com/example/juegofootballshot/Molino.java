package com.example.juegofootballshot;

import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Molino {
    GamePanel gamePanel ;
    Circle circle1;
    Circle circle2;
    public void showTargetMolino(GamePanel gP) {
        gamePanel=gP;
        Group molino = new Group();
        Rectangle body = new Rectangle();
        body.setX(100.0f);
        body.setY(300.0f);
        body.setWidth(30);
        body.setHeight(95);
        body.setFill(Color.PURPLE);
        body.setStroke(Color.BLACK);

        // create the sails of the mill
        Rectangle sail1 = new Rectangle();
        sail1.setX(75);
        sail1.setY(300.0f);
        sail1.setWidth(80.0f);
        sail1.setHeight(5.0f);
        sail1.setFill(Color.RED);
        sail1.setStroke(Color.BLACK);
// create a group for the circles
        Group circles = new Group();

// create two circles
        circle1 = new Circle(75, 300, 32);
        circle2 = new Circle(155, 300, 32);

// set the fill color for the circles
        circle1.setFill(new ImagePattern(new Image("bullseye.png")));
        circle2.setFill(new ImagePattern(new Image("bullseye.png")));

// add the circles to the group
        circles.getChildren().add(circle1);
        //circles.getChildren().add(sail1);
        circles.getChildren().add(circle2);
        molino.getChildren().addAll(body, sail1, circles);

// add the group to the root

        RotateTransition rotate2 = new RotateTransition(Duration.seconds(40), sail1);
        rotate2.setByAngle(360);
        rotate2.setCycleCount(Timeline.INDEFINITE);
        rotate2.setAutoReverse(false);


        // add the shapes to the group

        gamePanel.root.getChildren().add(molino);
        gamePanel.canvasJug.toFront();

        // create the rotation animation for the sails
        RotateTransition rotate1 = new RotateTransition(Duration.seconds(40), circles);
        rotate1.setByAngle(360);
        rotate1.setCycleCount(Timeline.INDEFINITE);
        rotate1.setAutoReverse(false);


        // create the translation animation for the mill
        TranslateTransition translate = new TranslateTransition(Duration.seconds(40), molino);
        translate.setFromX(900);
        translate.setToX(-200);
        translate.setCycleCount(Timeline.INDEFINITE);
        // add the shapes to the group


        // start the rotation and translation animations
        rotate1.play();
        rotate2.play();
        translate.play();
    }


}
