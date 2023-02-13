package com.example.juegofootballshot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimatedSpriteExample extends Application {
    int index = 0;

    Canvas canvas = new Canvas(900, 600);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    Image image = new Image("pixilartspritesheet.png");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Animated Sprite");
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        root.getChildren().add(canvas);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    startTimeline();
                }
            }
        });
     //   gc.drawImage(image,-100,70);
     //   gc.drawImage(image, (index % NUM_SPRITES) * (width / NUM_SPRITES), 0, (width / NUM_SPRITES), height, posX, posY, (width / NUM_SPRITES), height);

        primaryStage.show();
    }

    private void startTimeline() {
        int NUM_SPRITES = 10;
        double posX = -100;
        double posY = 100;
        double width = image.getWidth();
        double height = image.getHeight();

        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gc.clearRect(100, 250, 350, 350);
                gc.drawImage(image, (index % NUM_SPRITES) * (width / NUM_SPRITES), 0, (width / NUM_SPRITES), height, posX, posY, (width / NUM_SPRITES), height);
                index++;
                if (index == NUM_SPRITES) {
                    index = 0;
                    animation.stop();

                }
            }
        });
        animation.getKeyFrames().add(frame);

        animation.play();
    }
}
