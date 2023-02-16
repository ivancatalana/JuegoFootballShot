package com.example.juegofootballshot;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private Button jugar;
    @FXML
    private Button elegirNivel;

    @FXML
    private Button estadisticas;

    private GamePanel gamePanel;
    private MainApplication helloApplication;

    private String s = getClass().getClassLoader().getResource("RLForgethill.mp3").toExternalForm();
    private String s2 = getClass().getClassLoader().getResource("RLTuneshop.mp3").toExternalForm();

    private Media sound = new Media(s);
    private Media sound2 = new Media(s2);

    MediaPlayer audioClip = new MediaPlayer(sound);

    MediaPlayer audioClip2 = new MediaPlayer(sound2);


    @FXML
    protected void onJugarButtonClick() throws InterruptedException {

        helloApplication.hideShowStage(false);
        gamePanel = new GamePanel();
        gamePanel.start(new Stage());
        gamePanel.setMain(helloApplication);
        audioClip.stop();
        audioClip2.setCycleCount(MediaPlayer.INDEFINITE);
        audioClip2.play();
        audioClip2.setVolume(0.3);


    }

    public void setMain(MainApplication m) {
        helloApplication = m;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        audioClip.setCycleCount(MediaPlayer.INDEFINITE);
        audioClip.setVolume(0.3);
        audioClip.play();
    }

    public void showStatsTable(){
        ControllerStats controllerStats=new ControllerStats();
        controllerStats.showStatsTable2();
    }
}