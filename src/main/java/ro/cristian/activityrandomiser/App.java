package ro.cristian.activityrandomiser;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ro.cristian.activityrandomiser.model.Cuvant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    private final Logger logger = LogManager.getLogger("App: ");
    private static App instance;
    private ObservableList<Cuvant> cuvinte;

    private Stage stage;

    private Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        //System.out.println("Hello I reached here.");

        this.stage = stage;
        instance = this;

        cuvinte = FXCollections.observableArrayList(loadCuvinte());

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 450);
        stage.setTitle("Activity");

        stage.setScene(scene);
        stage.show();
    }

    private ArrayList<Cuvant> loadCuvinte(){
        ArrayList<Cuvant> cuvinte = new ArrayList<>();

        try {
            String path = new File(Launcher.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getAbsolutePath() + File.separator + "xml" + File.separator + "cuvinte.xml";

            //TODO: comment it out
            if(path.contains("target")) path = "src/main/resources/ro/cristian/activityrandomiser/xml/cuvinte.xml";
            System.out.println(path);

            File xmlCuvinte = new File(path);
            //an instance of factory that gives a document builder
            DocumentBuilderFactory dbf =  DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlCuvinte);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("c");

            for(int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if(node.getNodeType() ==  Node.ELEMENT_NODE){
                    Element eElement = (Element) node;
                    String c = eElement.getElementsByTagName("cuvant").item(0).getTextContent();
                    String t = eElement.getElementsByTagName("tip").item(0).getTextContent();
                    cuvinte.add(new Cuvant(c, t));
                }
            }

        }catch(Exception e){e.printStackTrace();}

        logger.info("Initial load of xml");
        for(var c : cuvinte){
            logger.info(c.getCuvant() + " " + c.getTipCuvant());
        }
        logger.info("Load complete");

        return cuvinte;
    }

    public static void main(String[] args) {
        launch();
    }

    public ObservableList<Cuvant> getCuvinte() {
        return cuvinte;
    }

    public static App getInstance() {
        return instance;
    }

    public double getWidth(){
        return scene.getWidth();
    }

    public double getHeight() {
        return scene.getHeight();
    }

    public Stage getStage(){
        return this.stage;
    }
}