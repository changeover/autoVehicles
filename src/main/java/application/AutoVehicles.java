package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import present.MainPanel;
import vehicle.Creature;


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
            int[] coord = new int[]{WINDOWWIDTH/2,WINDOWHEIGHT/2};
            crateLayers(applicationContext);
            applicationContext.getLightGrid().addSource( coord, 100);
            coord = new int[]{WINDOWWIDTH/2+100,WINDOWHEIGHT/2+100};
            applicationContext.getLightGrid().addSource( coord, 100);
            System.out.println(mainPane.getHeight());

            primaryStage.show();
            
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    public void crateLayers(ApplicationContext applicationContext){
    	Creature[][] dataLayer =new Creature[WINDOWWIDTH][WINDOWHEIGHT];
    	for (int i = 0 ; i<dataLayer.length; i++){
    		for (int y =0; y<dataLayer[i].length; y++){
    			dataLayer[i][y]=null;
    		}
    	}
    	applicationContext.getVehicleGrid().setData(dataLayer, "Vehicles");
    	
    	Double[][] valueLayer = new Double[WINDOWWIDTH][WINDOWHEIGHT];
    	for (int i = 0 ; i<valueLayer.length; i++){
    		for (int y =0; y<valueLayer[i].length; y++){
    			valueLayer[i][y]=(double) 0;
    		}
    	}
    	applicationContext.getLightGrid().setData(valueLayer, "Light Grid");
    	
    }
}
