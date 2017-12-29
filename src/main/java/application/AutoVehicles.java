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
            int[] coord = new int[]{500,500};
            crateLayers(applicationContext);
            applicationContext.getLightGrid().addSource( coord, 100);
            coord = new int[]{600,600};
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
    	Creature[][] dataLayer =new Creature[1000][1000];
    	for (int i = 0 ; i<dataLayer.length; i++){
    		for (int y =0; y<dataLayer.length; y++){
    			dataLayer[i][y]=null;
    		}
    	}
    	applicationContext.getVehicleGrid().setData(dataLayer, "Vehicles");
    	
    	Double[][] valueLayer = new Double[1000][1000];
    	for (int i = 0 ; i<valueLayer.length; i++){
    		for (int y =0; y<valueLayer.length; y++){
    			valueLayer[i][y]=(double) 0;
    		}
    	}
    	applicationContext.getLightGrid().setData(valueLayer, "Light Grid");
    	
    }
    
    public void createVehicle(ApplicationContext applicationContext){
    	Thread creatureThread = new Thread(new vehicle.Creature( applicationContext,300,300));
    	creatureThread.setDaemon(true);
    	creatureThread.start();
        
    }
}
