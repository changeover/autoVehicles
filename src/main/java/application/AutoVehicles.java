package application;

import grid.MainPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.Creature;
import world.GlobalClock;

public class AutoVehicles extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final int WINDOWWIDTH = 3000;
        final int WINDOWHEIGHT = 2000;
        ApplicationContext applicationContext = new ApplicationContext();
        Scene scene = new Scene(applicationContext.getMainPanel(),WINDOWWIDTH, WINDOWHEIGHT);
        primaryStage.setTitle("Autonomous vehicles");
        primaryStage.setScene(scene);

        GlobalClock clock = new GlobalClock(1, applicationContext.getLightMap());

        Thread creatureThread = new Thread(new vehicle.Creature(new int[1], applicationContext.getLightMap(),applicationContext.getMainPanel().getTopPane()));

        clock.start();

        primaryStage.show();
    }
}
