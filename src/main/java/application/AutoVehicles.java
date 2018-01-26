package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.vehicle.Creature;
import present.MainPanel;

/**
 * This is the Start of our little Prohramm, so it's deals
 * with the population of all models in the ApplicationContext
 * @author Andreas Ott, Sahin Bayram, Cesar de Carmo, Joel Zimmerli, Matthias Meichtry, Gregor von Gunten, Streiter Kevin
 *
 */
public class AutoVehicles extends Application {

    int WINDOWWIDTH;
    int WINDOWHEIGHT;
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	try {
            ApplicationContext applicationContext = new ApplicationContext();

            WINDOWWIDTH = applicationContext.getWindowWidth();
            WINDOWHEIGHT = applicationContext.getWindowHeight();
            
            MainPanel mainPane = new MainPanel(applicationContext);
            Scene scene = new Scene(mainPane,applicationContext.getWindowWidth(),mainPane.getMinHeight());
            primaryStage.setTitle("Autonomous vehicles");
            primaryStage.setScene(scene);
            createLayers(applicationContext);
            int[] coord = new int[]{WINDOWWIDTH/2,WINDOWHEIGHT/2};
            applicationContext.getLightGrid().addSource( coord);
            primaryStage.show();
            
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    private void createLayers(ApplicationContext applicationContext){
    	Creature[][] creatureLayer =new Creature[WINDOWWIDTH][WINDOWHEIGHT];
    	for (int i = 0 ; i<creatureLayer.length; i++){
    		for (int y =0; y<creatureLayer[i].length; y++){
    			creatureLayer[i][y]=null;
    		}
    	}
    	applicationContext.getVehicleGrid().setData(creatureLayer, "Vehicles");
    	
    	Double[][] lightLayer = new Double[WINDOWWIDTH][WINDOWHEIGHT];
    	for (int i = 0 ; i<lightLayer.length; i++){
    		for (int y =0; y<lightLayer[i].length; y++){
    			lightLayer[i][y]=(double) 0;
    		}
    	}
    	applicationContext.getLightGrid().setData(lightLayer, "Light Grid");
    	
    }
}
