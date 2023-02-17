package com.example.juegofootballshot;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
    private Text difficultyText = new Text("O");
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

    int puntosUltimaPartida;
    int opcionSeleccionada = 1;


    @FXML
    protected void onJugarButtonClick() throws InterruptedException {

        helloApplication.hideShowStage(false);

        gamePanel = new GamePanel();
        GamePanel.speed1=opcionSeleccionada;

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
    public MenuController getMenuController(){
        return this;
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
    public void setDifficulty() {

        VBox vbox = new VBox();

        Button btnFacil = new Button("Facil");
        Button btnMedio = new Button("Medio");
        Button btnDificil = new Button("Dificil");
        Button btnExtremo = new Button("Extremo");

        btnFacil.setOnAction(event -> {
            opcionSeleccionada = 1;
            difficultyText.setText("O");
            ((Stage) vbox.getScene().getWindow()).close();
        });

        btnMedio.setOnAction(event -> {
            difficultyText.setText("   O");
            opcionSeleccionada = 2;
            ((Stage) vbox.getScene().getWindow()).close();
        });

        btnDificil.setOnAction(event -> {
            opcionSeleccionada = 3;
            difficultyText.setText("      O");

            ((Stage) vbox.getScene().getWindow()).close();
        });

        btnExtremo.setOnAction(event -> {
            opcionSeleccionada = 4;
            difficultyText.setText("         O");

            ((Stage) vbox.getScene().getWindow()).close();
        });

        vbox.getChildren().addAll(btnFacil, btnMedio, btnDificil, btnExtremo);

        Stage dialogo = new Stage();
        dialogo.initModality(Modality.APPLICATION_MODAL);
        Image backgroundImage = new Image("tablebackgroundr.gif");
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background background = new Background(backgroundImg);
        vbox.setBackground(background);


        Scene scene = new Scene(vbox,300,200);
        dialogo.setScene(scene);
        dialogo.showAndWait();

    }


}