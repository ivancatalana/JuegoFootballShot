package com.example.juegofootballshot;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ControllerStats {
    private static Stage secondaryStage;
    @FXML
    private TextField textFieldWinner;
   // @FXML
  //  private void initialize() {
    //    textFieldWinner.setText(Controller.textLastWinnerName);
    //}

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    int puntuacion;
    public void showStatsName (int puntuacionPartida) throws IOException {
        setPuntuacion(puntuacionPartida);
        System.out.println("A la Classe ControllerStats = "+puntuacionPartida);
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("stats-name.fxml"));
        VBox statsName = loader.load();

        statsName.setBackground(
                Background.fill(new LinearGradient(
                0, 0, 1, 1, true,                      //sizing
                CycleMethod.NO_CYCLE,                  //cycling
                new Stop(0, Color.web("#F6F2E5")),     //colors
                new Stop(1, Color.web("#7B7A79")))));
        Scene scene = new Scene(statsName,320,240);
        secondaryStage = new Stage();
        secondaryStage.setScene(scene);
     //   secondaryStage.setTitle(Controller.textLastWinner.getText());
        secondaryStage.show();
        // Set person overview into the center of root layout.

    }
    public void acceptNameStats(){
        String winnerName= textFieldWinner.getText();
        if (!winnerName.equals(" ")) {
            setFileWriterwinner(winnerName,getPuntuacion());
            System.out.println(puntuacion);
           // Controller.textLastWinnerName = winnerName;
            secondaryStage.close();
            System.out.println(getPuntuacion());
        }

    }
    public void closeStats()  {
        secondaryStage.close();
    }

    private void setFileWriterwinner(String td,int puntosStat) {
        System.out.println("FileWrter "+ puntosStat);
        ArrayList<Persona>personas=new ArrayList<>();
        List<List<String>> records = new ArrayList<>();
        boolean exist=false;
        try {
            FileReader fileReader = new FileReader("src/main/resources/com/example/juegofootballshot/stats.txt");
            CSVReader csvReader = new CSVReader(fileReader);
            String[] values;
            String fileTxt="";
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
            for(List l : records) {
                          personas.add(new Persona(l.get(0).toString(),Integer.parseInt(l.get(1).toString()),Integer.parseInt(l.get(2).toString())));
            }
            for(Persona p:personas){
                if (td.equals(p.getName())){
                    p.setPartidasJugadas(p.getPartidasJugadas()+1);
                    if(p.getPuntuacionMaxima()<puntuacion)
                    p.setPuntuacionMaxima(puntuacion);
                    exist=true;
                }
            }
            if (!exist){
                personas.add(new Persona(td,1,puntuacion));
            }

            for (Persona p:personas){
                fileTxt += p.getName()+","+p.getPartidasJugadas()+","+p.getPuntuacionMaxima()+"\n";
            }
            FileWriter fileWriter = new FileWriter(new File("src/main/resources/com/example/juegofootballshot/stats.txt"));
            fileWriter.write(fileTxt);
            fileWriter.close();
            textFieldWinner.setText(td);

        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showStatsTable2() {


        ArrayList<Persona>personas=new ArrayList<>();
        List<List<String>> records = new ArrayList<List<String>>();

        try {
            FileReader fileReader = new FileReader("src/main/resources/com/example/juegofootballshot/stats.txt");
            CSVReader csvReader = new CSVReader(fileReader);
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
            for(List l : records) {
                personas.add(new Persona(l.get(0).toString(),Integer.parseInt(l.get(1).toString()),Integer.parseInt(l.get(2).toString())));
                System.out.println(l.get(0).toString()+" "+ Integer.parseInt(l.get(1).toString()) +" "+Integer.parseInt(l.get(2).toString()));
            }


        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }

        personas.sort((o1, o2) -> o1.compareTo(o2));
        int contadorRanking=1;
        List<String> rankValues = new LinkedList<>();
        List<String> stringValues = new LinkedList<>();
        List<Integer> intValues = new LinkedList<>();
        List<Integer> intValuesP = new LinkedList<>();

        Stage s = new Stage();
        s.setTitle("Ranking de ganadores");

        for(Persona p:personas) {
            rankValues.add("   "+contadorRanking+"º");
            contadorRanking++;
            stringValues.add(p.getName());
            intValues.add(p.getPartidasJugadas());
            intValuesP.add(p.getPuntuacionMaxima());
        }

    TableView<Integer> table = new TableView<>();
        for (int i = 0; i < intValues.size() && i < stringValues.size()&&i < intValuesP.size(); i++) {
        table.getItems().add(i);
    }
        TableColumn<Integer, String> rankColumn = new TableColumn<>("Rank");
        List<String> rankingIntValues = rankValues;
        rankColumn.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(rankingIntValues.get(rowIndex));
        });

        TableColumn<Integer, String> nameColumn = new TableColumn<>("Name");
        List<String> finalStringValues = stringValues;
        nameColumn.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(finalStringValues.get(rowIndex));
        });

    TableColumn<Integer, Number> intColumn = new TableColumn<>("Games");
        List<Integer> finalIntValues = intValues;
        intColumn.setCellValueFactory(cellData -> {
        Integer rowIndex = cellData.getValue();
        return new ReadOnlyIntegerWrapper(finalIntValues.get(rowIndex));
    });
        TableColumn<Integer, Number> intColumnP = new TableColumn<>("MaxScore");
        List<Integer> finalIntValuesP = intValuesP;
        intColumnP.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyIntegerWrapper(finalIntValuesP.get(rowIndex));
        });
           // Crea una imagen de fondo personalizada
        Image backgroundImage = new Image("tablebackgroundr.gif");
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
          // Establece la imagen de fondo personalizada como el fondo de la tabla

        table.getColumns().add(rankColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(intColumnP);
        table.getColumns().add(intColumn);

// Crea un AnchorPane y agrega la imagen de fondo y la tabla
        Background background = new Background(backgroundImg);
        AnchorPane anchorPane = new AnchorPane();

        anchorPane.setBackground(background);
        table.maxHeight(200);
        table.maxWidth(200);
        AnchorPane.setTopAnchor(anchorPane, 100.0);
        AnchorPane.setLeftAnchor(anchorPane, 100.0);
        anchorPane.getChildren().add(table);

        Scene scene = new  Scene(anchorPane, 750, 450);
        s.setScene(scene);
        s.show();
}
}
