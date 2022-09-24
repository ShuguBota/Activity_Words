module ro.cristian.activityrandomiser {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires java.xml;

//    opens ro.cristian.activityrandomiser to javafx.fxml;
//    exports ro.cristian.activityrandomiser;
//    exports ro.cristian.activityrandomiser.model;
//    opens ro.cristian.activityrandomiser.model to javafx.fxml;
//    exports ro.cristian.activityrandomiser.controller;
    opens ro.cristian.activityrandomiser.controller to javafx.fxml, javafx.base, javafx.graphics;
    opens ro.cristian.activityrandomiser.model to javafx.fxml, javafx.base, javafx.graphics;
    opens ro.cristian.activityrandomiser to javafx.graphics, javafx.base, javafx.fxml;
    //opens ro.cristian.activityrandomiser.view to javafx.graphics, javafx.base, javafx.fxml;
}