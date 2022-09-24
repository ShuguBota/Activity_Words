package ro.cristian.activityrandomiser.controller;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.cristian.activityrandomiser.App;
import ro.cristian.activityrandomiser.model.Cuvant;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class AppController implements Initializable{

    private final Logger logger = LogManager.getLogger("AppController: ");

    @FXML private Label labelDesenat;
    @FXML private Label labelVorbit;
    @FXML private Label labelMimat;
    private SimpleIntegerProperty ramaseDesenat;
    private SimpleIntegerProperty ramaseVorbit;
    private SimpleIntegerProperty ramaseMimat;

    @FXML private Label labelCuvantGenerat;
    private SimpleStringProperty cuvantGenerat;

    private boolean firstTime = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        var cuvinte = App.getInstance().getCuvinte();
        ramaseDesenat = new SimpleIntegerProperty(gasesteCuvinte("desenat", cuvinte).size());
        ramaseVorbit = new SimpleIntegerProperty(gasesteCuvinte("vorbit", cuvinte).size());
        ramaseMimat = new SimpleIntegerProperty(gasesteCuvinte("mimat", cuvinte).size());
        labelDesenat.textProperty().bind(ramaseDesenat.asString());
        labelVorbit.textProperty().bind(ramaseVorbit.asString());
        labelMimat.textProperty().bind(ramaseMimat.asString());
        cuvantGenerat = new SimpleStringProperty("");
        labelCuvantGenerat.textProperty().bind(cuvantGenerat);
        labelCuvantGenerat.setFont(Font.font(80));

        App.getInstance().getStage().widthProperty().addListener((o, oldWidth, newWidth) -> {
            resize();
        });
        App.getInstance().getStage().heightProperty().addListener((o, oldHeight, newHeight) -> {
            resize();
        });
    }

    @FXML
    private void buttonDesenatPressed(){
        var cuvinte = App.getInstance().getCuvinte();
        ArrayList<Cuvant> cuvinteDesenat = gasesteCuvinte("desenat", cuvinte);

        if(cuvinteDesenat.size() != 0){
            var x = cuvinteDesenat.get(getRandom(cuvinteDesenat.size()));
            cuvinte.remove(x);
            ramaseDesenat.set(ramaseDesenat.get() - 1);
            cuvantGenerat.set(x.getCuvant());
            resize();
            System.out.println(cuvinte.size() + " " + x.getCuvant() + " " + x.getTipCuvant());
        }
        else{
            alerta("desenat");
        }
    }

    @FXML
    private void buttonVorbitPressed(){
        var cuvinte = App.getInstance().getCuvinte();
        ArrayList<Cuvant> cuvinteVorbit = gasesteCuvinte("vorbit", cuvinte);

        if(cuvinteVorbit.size() != 0){
            var x = cuvinteVorbit.get(getRandom(cuvinteVorbit.size()));
            cuvinte.remove(x);
            ramaseVorbit.set(ramaseVorbit.get() - 1);
            cuvantGenerat.set(x.getCuvant());
            resize();
            System.out.println(cuvinte.size() + " " + x.getCuvant() + " " + x.getTipCuvant());
        }
        else{
            alerta("vorbit");
        }
    }

    @FXML
    private void buttonMimatPressed(){
        var cuvinte = App.getInstance().getCuvinte();
        ArrayList<Cuvant> cuvinteMimat = gasesteCuvinte("mimat", cuvinte);

        if(cuvinteMimat.size() != 0){
            var x = cuvinteMimat.get(getRandom(cuvinteMimat.size()));
            cuvinte.remove(x);
            ramaseMimat.set(ramaseMimat.get() - 1);
            cuvantGenerat.set(x.getCuvant());
            resize();
            System.out.println(cuvinte.size() + " " + x.getCuvant() + " " + x.getTipCuvant());
        }
        else{
            alerta("mimat");
        }
    }

    private ArrayList<Cuvant> gasesteCuvinte(String tip, ObservableList<Cuvant> cuvinte) {
        ArrayList<Cuvant> cuvinteSelectate = new ArrayList<>();
        for (var c : cuvinte) {
            if (c.getTipCuvant().equals(tip))
                cuvinteSelectate.add(c);
        }
        return cuvinteSelectate;
    }

    private int getRandom(int size){
        return (LocalDateTime.now().getSecond() * LocalDateTime.now().getNano()) % size;
    }

    private void alerta(String tip){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("Nu mai sunt cuvinte de " + tip);
        a.show();
    }

    private void resize(){
        System.out.println("Window has been resized");
        double height = App.getInstance().getStage().getHeight();
        double width = App.getInstance().getStage().getWidth();
        double fontSize = height * width * 0.002;
        System.out.println("The font size is " + fontSize);
        double litere = labelCuvantGenerat.getText().length();
        if(litere != 0 && fontSize != 0){
            System.out.println("The final font size is" + (fontSize/litere));
            if(litere <= 5){
                labelCuvantGenerat.setFont(Font.font(fontSize / 5));
            }
            else {
                labelCuvantGenerat.setFont(Font.font(fontSize / (litere)));
            }
        }
    }
}