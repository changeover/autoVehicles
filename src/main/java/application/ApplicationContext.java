package application;


import grid.GridWorldSources;
import grid.impl.lightDataLayer;
import grid.impl.vehicelsDataLyer;
import logic.SettingsController;
import logic.impl.SettingsControllerImpl;
import vehicle.Creature;
import world.LightMap;


public class ApplicationContext {
    private SettingsController settingsController;
    private GridWorldSources<Integer> lightGrid;
    private vehicelsDataLyer<Creature> vehicleGrid;
	private LightMap lightMap;

    public ApplicationContext(){
        settingsController = new SettingsControllerImpl();
        this.lightMap = new LightMap(10, 10);
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

    public LightMap getLightMap() {
        return lightMap;
    }
}
