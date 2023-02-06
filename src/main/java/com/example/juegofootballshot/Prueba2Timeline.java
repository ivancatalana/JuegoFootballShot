package com.example.juegofootballshot;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Prueba2Timeline extends Application {

    @Override
    public void start(Stage stage) {

        VBox vb = new VBox();

        Rectangle rect = new Rectangle(100, 100, 50, 50);
        rect.setManaged(false);
        rect.setArcHeight(50);
        rect.setArcWidth(50);
        rect.setFill(Color.VIOLET);

        final Duration SEC_2 = Duration.millis(2000);

        System.out.println("Location before relocation = " + rect.getX() + ","
                + rect.getY() + ")");

        Timeline timeline = new Timeline();

        KeyFrame end = new KeyFrame(SEC_2,
                new KeyValue(rect.xProperty(), 400),
                new KeyValue(rect.yProperty(), 400));

        timeline.getKeyFrames().add(end);

        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Location after relocation = " + rect.getX()
                        + "," + rect.getY() + ")");
            }
        });
        timeline.play();

        vb.getChildren().add(rect);

        Scene scene = new Scene(vb, 500, 500);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
