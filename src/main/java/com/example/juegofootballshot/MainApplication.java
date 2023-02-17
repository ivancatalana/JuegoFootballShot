package com.example.juegofootballshot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public Stage getStage() {
        return stage;
    }

    MenuController controller;
    private Stage stage;
    MediaPlayer mediaPlayer;

    @Override
    public void start(Stage s) throws IOException {
        stage = s;
        GamePanel gamePanel;
        stage.setTitle("Football Shot");
        Scene scene;
        String mediaPath = "IntroF.mp4";
        Media media = new Media(getClass().getClassLoader().getResource(mediaPath).toString());
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        scene = new Scene(new Group(mediaView), 900, 600);
        stage.setScene(scene);
        stage.show();

        mediaPlayer.play();
        // When the video ends or the space key is pressed, hide the video and show the game panel
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.stop());
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                try {
                    showMenuPanel();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        stage.setScene(scene);
        stage.show();
    }

    private void showMenuPanel() throws IOException {
        // Stop the video
        mediaPlayer.stop();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("pantalla-inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        controller = (MenuController) fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Football Shot");
        stage.setScene(scene);
        stage.show();


    }

    public void hideShowStage(boolean s) {
        boolean showStage = s;
        if (showStage == false)
            stage.hide();
        else {
            stage.show();
            controller.audioClip2.stop();
            controller.audioClip.play();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}