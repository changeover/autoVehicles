package application;


import grid.GridWorldSources;
import grid.impl.lightDataLayer;
import grid.impl.vehicelsDataLyer;
import logic.SettingsController;
import logic.impl.SettingsControllerImpl;
import vehicle.Creature;


public class ApplicationContext {
    private SettingsController settingsController;
    private GridWorldSources<Integer> lightGrid;
    private vehicelsDataLyer<Creature> vehicleGrid;

    public ApplicationContext(){
        settingsController = new SettingsControllerImpl();
        lightGrid = new lightDataLayer<Integer>();
        vehicleGrid = new vehicelsDataLyer<>();

    }

    public void run(){

    }
    public void stop(){

    }
    public GridWorldSources<Integer> getLightGrid() {
		return lightGrid;
	}

	public vehicelsDataLyer<Creature> getVehicleGrid() {
		return vehicleGrid;
	}
    public SettingsController getSettingsController(){
        return this.settingsController;
    }

}
