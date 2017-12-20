package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.CreatureLogic;
import logic.impl.CreatureLogicImpl;
import world.GlobalClock;

public class AutoVehicles extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final int WINDOWWIDTH = 1400;
        final int WINDOWHEIGHT = 800;
        ApplicationContext applicationContext = new ApplicationContext();
        Scene scene = new Scene(applicationContext.getMainPanel(),WINDOWWIDTH, WINDOWHEIGHT);
        primaryStage.setTitle("Autonomous vehicles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
