package com.example.juegofootballshot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public Stage getStage() {
        return stage;
    }

    private Stage stage ;
    @Override
    public void start(Stage s) throws IOException {
        stage=s;
        GamePanel gamePanel;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("pantalla-inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        MenuController controller= (MenuController) fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Football Shot");
        stage.setScene(scene);

        stage.show();

    }
    public void hideShowStage(boolean s){
        boolean showStage = s;
        if (showStage==false)
        stage.hide();
        else stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}