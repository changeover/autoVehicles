package present;

import application.ApplicationContext;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import present.settings.Settings;
/**
 * Main Panel with all components.
 * @author Andreas Ott, Matthias Meichtry, Sahin Bayram
 *
 */

public class MainPanel extends BorderPane{
	private WorldPage front;
    public MainPanel(final ApplicationContext applicationContext){
        final int SETTINGHEIGHT = 100;
        StackPane visualPane = new StackPane();
        StackPane settingPane = new StackPane(new Settings(applicationContext));
        TilePane buttonPane = new TilePane();
        buttonPane.setPadding(new Insets(5,10,5,10));
        buttonPane.setHgap(30);
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.setDividerPositions(1,SETTINGHEIGHT/applicationContext.getWindowHeight());
        splitPane.setDividerPositions(0,(double)(applicationContext.getWindowHeight() - SETTINGHEIGHT) / (double)applicationContext.getWindowHeight());
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        Button exitButton = new Button("Exit");

        front = new WorldPage(applicationContext);

        buttonPane.setMaxHeight(30);
        settingPane.setMaxHeight(30);
        visualPane.setMinHeight(applicationContext.getWindowHeight());
        splitPane.setMaxWidth(applicationContext.getWindowWidth());
        splitPane.getItems().addAll(buttonPane,visualPane,settingPane);


        buttonPane.getChildren().addAll(startButton,stopButton,exitButton);
        startButton.setOnAction(event -> {
        	 applicationContext.getVehicleGrid().deleteVehicles();
             applicationContext.getSettingsController().deactivateSlider();
             int vehiclesAmount = applicationContext.getSettingsController().getVehicleCount();
             for(int i = 0; i < vehiclesAmount; i++ ){
                 double randomX = Math.random();
                 double randomY = Math.random();
                 applicationContext.getVehicleGrid().createVehicle((int)(
                		 (randomX*applicationContext.getWindowWidth())-(randomX*20)),
                		 (int)((randomY*applicationContext.getWindowHeight())-(randomY*20)));
             }
        });
        stopButton.setOnAction(event -> {
        	applicationContext.getSettingsController().activateSlider();
            applicationContext.getVehicleGrid().deleteVehicles();
        });
        exitButton.setOnAction(event -> System.exit(0));

        visualPane.getChildren().add(front);

        setCenter(splitPane);

    }
}

