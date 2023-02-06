package com.example.juegofootballshot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicReference;

public class TimelineObstaculo extends Application {
    private double x = 900;
    private double y = 250;
    private double radius = 20;
    double angle = 0;

    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle(x, y, radius, Color.RED);

        Pane root = new Pane();
        root.getChildren().add(circle);

        Scene scene = new Scene(root, 1000, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        double speed = 1;
        radius = 1;
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            angle= (angle + speed) % 360;
            circle.setCenterX(circle.getCenterX() - speed);
            circle.setCenterY(circle.getCenterY() + radius * Math.cos(Math.toRadians(angle)));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}