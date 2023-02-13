package com.example.juegofootballshot;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BarraEnergia extends Application {
    private ProgressBar energyBar;
    private ProgressBar energyBar2;

    private double energyLevel = 0.0;

    @Override
    public void start(Stage stage) {
        energyBar2 = new ProgressBar(100);
        energyBar2.setPrefWidth(200);
        energyBar2.getStylesheets().add(getClass().getResource("energybar.css").toExternalForm());



        energyBar = new ProgressBar(0.0);
        energyBar.setPrefWidth(200);
        //energyBar.getStylesheets().add(getClass().getResource("energybar.css").toExternalForm());


        Button btnIncreaseEnergy = new Button("Aumentar energía");
        btnIncreaseEnergy.setOnAction(e -> increaseEnergy());

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        root.getChildren().addAll(energyBar2,energyBar, btnIncreaseEnergy);

        Scene scene = new Scene(root, 300, 250);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                increaseEnergy();
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    private void increaseEnergy() {
        energyLevel += 0.1;
        if (energyLevel > 1.0) {
            energyLevel = 1.0;
        }
        energyBar.setProgress(energyLevel);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
