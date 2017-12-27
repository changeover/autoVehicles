package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import present.MainPanel;
import vehicle.Creature;


public class AutoVehicles extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	try {
    		final int WINDOWWIDTH = 1000;
            final int WINDOWHEIGHT = 800;
            ApplicationContext applicationContext = new ApplicationContext();
            
            MainPanel mainPane = new MainPanel(applicationContext,WINDOWWIDTH,WINDOWHEIGHT);
            Scene scene = new Scene(mainPane,WINDOWWIDTH, WINDOWHEIGHT);
            primaryStage.setTitle("Autonomous vehicles");
            primaryStage.setScene(scene);
            int[] coord = new int[]{3,3};
            crateLayers(applicationContext);
            applicationContext.getLightGrid().addSource( coord, 100);


            
            createVehicle(applicationContext);
            createVehicle(applicationContext);
            createVehicle(applicationContext);
            createVehicle(applicationContext);

            primaryStage.show();
            
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    public void crateLayers(ApplicationContext applicationContext){
    	Creature[][] dataLayer =new Creature[10000][10000];
    	for (int i = 0 ; i<dataLayer.length; i++){
    		for (int y =0; y<dataLayer.length; y++){
    			dataLayer[i][y]=null;
    		}
    	}
    	applicationContext.getVehicleGrid().setData(dataLayer, "Vehicles");
    	
    	Integer[][] valueLayer = new Integer[100][100];
    	for (int i = 0 ; i<valueLayer.length; i++){
    		for (int y =0; y<valueLayer.length; y++){
    			valueLayer[i][y]=i+y;
    		}
    	}
    	applicationContext.getLightGrid().setData(valueLayer, "Light Grid");
    	
    }
    
    public void createVehicle(ApplicationContext applicationContext){
    	Thread creatureThread = new Thread(new vehicle.Creature(new int[] {1,3}, applicationContext));
    	creatureThread.setDaemon(true);
    	creatureThread.start();
        
    }
}
