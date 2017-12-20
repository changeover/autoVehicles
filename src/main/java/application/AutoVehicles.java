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
        final int WINDOWWIDTH = 1000;
        final int WINDOWHEIGHT = 800;
        ApplicationContext applicationContext = new ApplicationContext();
        Pane mainPane = new MainPanel(applicationContext,WINDOWWIDTH,WINDOWHEIGHT);
        Scene scene = new Scene(mainPane,WINDOWWIDTH, WINDOWHEIGHT);
        primaryStage.setTitle("Autonomous vehicles");
        primaryStage.setScene(scene);
        
        int[] coord = new int[]{3,3};
        applicationContext.getLightGrid().addSource( coord, 100);
        GlobalClock clock = new GlobalClock(1, applicationContext);

        Thread creatureThread = new Thread(new vehicle.Creature(new int[] {1,3}, applicationContext));
        creatureThread.start();

        clock.start();

        primaryStage.show();
    }
}
