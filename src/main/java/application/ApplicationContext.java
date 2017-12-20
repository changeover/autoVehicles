package application;


import grid.GridWorldSources;
import grid.GridWorldVehicle;
import grid.impl.lightDataLayer;
import grid.impl.vehicelsDataLyer;
import logic.*;
import logic.impl.*;
import world.LightMap;

public class ApplicationContext {
    private SettingsController settingsController;
    private GridWorldSources<Integer> lightGrid;
    private GridWorldVehicle<Creature> vehicleGrid;
	private LightMap lightMap;

    public ApplicationContext(){
        settingsController = new SettingsControllerImpl();
        this.lightMap = new LightMap(10, 10);
        lightGrid = new lightDataLayer<Integer>();
        vehicleGrid = new vehicelsDataLyer<Creature>();

    }

    public void run(){

    }
    public void stop(){

    }
    public GridWorldSources<Integer> getLightGrid() {
		return lightGrid;
	}

	public GridWorldVehicle<Creature> getVehicleGrid() {
		return vehicleGrid;
	}
    public SettingsController getSettingsController(){
        return this.settingsController;
    }

    public LightMap getLightMap() {
        return lightMap;
    }
}
