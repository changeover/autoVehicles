package application;



import grid.impl.LightLayer;
import grid.impl.VehicleDataLayer;
import javafx.scene.image.Image;
import logic.SettingsController;
import logic.impl.SettingsControllerImpl;
import vehicle.Creature;


public class ApplicationContext {

    private int WINDOWWIDTH = 1000;
    private int WINDOWHEIGHT = 800;
    private SettingsController settingsController;
    private LightLayer lightGrid;
    private VehicleDataLayer<Creature> vehicleGrid;
    private Image image;
    public ApplicationContext(){
        settingsController = new SettingsControllerImpl();
        lightGrid = new LightLayer();
        vehicleGrid = new VehicleDataLayer<>();
        image = new Image("VehicleImage.jpg");
    }

    public void run(){
        deleteVehicles();
        settingsController.deactivateSlider();
        int vehiclesAmount = settingsController.getVehicleCount();
        for(int i = 0; i < vehiclesAmount; i++ ){
            double randomX = Math.random();
            double randomY = Math.random();
            createVehicle((int)((randomX*WINDOWWIDTH)-(randomX*20)), (int)((randomY*WINDOWHEIGHT)-(randomY*20)));
        }

    }
    public void stop(){
        settingsController.activateSlider();
        deleteVehicles();
    }

    public LightLayer getLightGrid() {
		return lightGrid;
	}

	public VehicleDataLayer<Creature> getVehicleGrid() {
		return vehicleGrid;
	}
    public SettingsController getSettingsController(){
        return this.settingsController;
    }

    public Image getImage(){
        return image;
    }

    private void createVehicle(int x, int y){
        Thread creatureThread = new Thread(new vehicle.Creature( this,x,y));
        creatureThread.setDaemon(true);
        creatureThread.start();
    }

    private void deleteVehicles(){

        vehicleGrid.clearData();
       }

    public int getWindowWidth() {
        return WINDOWWIDTH;
    }

    public int getWindowHeight() {
        return WINDOWHEIGHT;
    }
}
