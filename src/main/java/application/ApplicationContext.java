package application;



import grid.impl.LightDataLayer;
import grid.impl.vehicelsDataLyer;
import logic.SettingsController;
import logic.impl.SettingsControllerImpl;
import vehicle.Creature;


public class ApplicationContext {
    private SettingsController settingsController;
    private LightDataLayer lightGrid;
    private vehicelsDataLyer<Creature> vehicleGrid;
    public ApplicationContext(){
        settingsController = new SettingsControllerImpl();
        lightGrid = new LightDataLayer();
        vehicleGrid = new vehicelsDataLyer<>();

    }

    public void run(){

    }
    public void stop(){

    }
    public LightDataLayer getLightGrid() {
		return lightGrid;
	}

	public vehicelsDataLyer<Creature> getVehicleGrid() {
		return vehicleGrid;
	}
    public SettingsController getSettingsController(){
        return this.settingsController;
    }

}
