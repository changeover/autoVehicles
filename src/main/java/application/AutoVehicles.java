package application;

import grid.MainPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import world.GlobalClock;


public class AutoVehicles extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final int WINDOWWIDTH = 1000;
        final int WINDOWHEIGHT = 800;
        ApplicationContext applicationContext = new ApplicationContext(WINDOWWIDTH, WINDOWHEIGHT);
        MainPanel mainPane = new MainPanel(applicationContext, WINDOWWIDTH, WINDOWHEIGHT);


        Scene scene = new Scene(mainPane, WINDOWWIDTH, WINDOWHEIGHT);
        primaryStage.setTitle("Autonomous vehicles");
        primaryStage.setScene(scene);

        GlobalClock clock = new GlobalClock(applicationContext.getWorldPane());


        //temp
        for (int i = 0; i < 300; i++) {

            applicationContext.getWorldPane().placeCreature(new vehicle.Creature(applicationContext), Math.random() * 990, Math.random() * 790);
        }
        clock.start();
        primaryStage.show();
    }
}
