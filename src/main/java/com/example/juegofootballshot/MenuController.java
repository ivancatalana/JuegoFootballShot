package com.example.juegofootballshot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuController {
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
    @FXML
    protected void onJugarButtonClick() throws InterruptedException {

        helloApplication.hideShowStage(false);
        gamePanel=new GamePanel();
       gamePanel.start(new Stage());


    }
    public void setMain(MainApplication m){
        helloApplication=m;
    }
}