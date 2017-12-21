package application;


import grid.GridWorldSources;
import grid.impl.BackgroundPic;
import grid.impl.lightDataLayer;
import grid.impl.vehicelsDataLyer;
import logic.SettingsController;
import logic.impl.SettingsControllerImpl;
import vehicle.Creature;


public class ApplicationContext {
    private SettingsController settingsController;
    private lightDataLayer lightGrid;
    private vehicelsDataLyer<Creature> vehicleGrid;
    private BackgroundPic background;
    public ApplicationContext(){
        settingsController = new SettingsControllerImpl();
        lightGrid = new lightDataLayer();
        vehicleGrid = new vehicelsDataLyer<>();
        background = new BackgroundPic(lightGrid);

    }

    public void run(){

    }
    public void stop(){

    }
    public lightDataLayer getLightGrid() {
		return lightGrid;
	}

	public vehicelsDataLyer<Creature> getVehicleGrid() {
		return vehicleGrid;
	}
    public SettingsController getSettingsController(){
        return this.settingsController;
    }
    public BackgroundPic getBackgroudnPic(){
    	return background;
    }

}
