package com.example.juegofootballshot;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class EnergyBarExample extends Application {
    private Pane energyBarPane;
    private Line energyLine;
    private int energyCounter = 0;

    @Override
    public void start(Stage stage) {
        Rectangle energyBarBackground = new Rectangle(100, 20);
        energyBarBackground.setFill(Color.LIGHTGRAY);
        energyBarBackground.setStroke(Color.DARKGRAY);
        energyBarBackground.setStrokeWidth(1);

        Rectangle greenBar = new Rectangle(10, 20);
        greenBar.setFill(Color.GREEN);

        Rectangle yellowBar = new Rectangle(5, 20);
        yellowBar.setFill(Color.YELLOW);

        Rectangle redBar = new Rectangle(40, 20);
        redBar.setFill(Color.RED);

        Rectangle yellowBar2 = new Rectangle(5, 20);
        yellowBar2.setFill(Color.YELLOW);

        Rectangle redBar2 = new Rectangle(40, 20);
        redBar2.setFill(Color.RED);

        energyLine = new Line(0, 10, 0, 10);
        energyLine.setStroke(Color.BLACK);
        energyLine.setStrokeWidth(2);

        HBox colorBars = new HBox(0);
        colorBars.getChildren().addAll(redBar,yellowBar,greenBar, yellowBar2, redBar2);

        energyBarPane = new Pane();
        energyBarPane.setPrefSize(120, 40);
        energyBarPane.getChildren().addAll(energyBarBackground, colorBars, energyLine);

        Button btnIncreaseCounter = new Button("Aumentar contador");
        btnIncreaseCounter.setOnAction(e -> increaseCounter());

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(energyBarPane, btnIncreaseCounter);

        Scene scene = new Scene(root, 200, 200);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    increaseCounter();
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    private void increaseCounter() {
        energyCounter = Math.min(100, energyCounter + 10);
        energyLine.setEndX(energyCounter);
    }

    public static void main(String[] args) {
        launch(args);
    }
}