package application;

import grid.MainPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
        primaryStage.show();
    }
}
