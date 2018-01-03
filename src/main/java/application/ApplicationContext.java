package application;



import grid.impl.LightDataLayer;
import grid.impl.VehicelDataLayer;
import javafx.scene.image.Image;
import logic.SettingsController;
import logic.impl.SettingsControllerImpl;
import vehicle.Creature;


public class ApplicationContext {
    private SettingsController settingsController;
    private LightDataLayer lightGrid;
    private VehicelDataLayer<Creature> vehicleGrid;
    private Image image;
    public ApplicationContext(){
        settingsController = new SettingsControllerImpl();
        lightGrid = new LightDataLayer();
        vehicleGrid = new VehicelDataLayer<>();
        image = new Image("VehicleImage.jpg");

    }

    public void run(){
        deleteVehicles();
        settingsController.deactivateSlider();
        int vehiclesAmount = settingsController.getVehicleCount();
        for(int i = 0; i <= vehiclesAmount; i++ ){
            createVehicle((int)(Math.random()*1000), (int)(Math.random()*800));
        }

    }
    public void stop(){
        settingsController.activateSlider();
        deleteVehicles();
    }

    public LightDataLayer getLightGrid() {
		return lightGrid;
	}

	public VehicelDataLayer<Creature> getVehicleGrid() {
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
}
