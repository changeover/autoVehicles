package application;


import logic.*;
import logic.impl.*;
import world.LightMap;

public class ApplicationContext {
    private SettingsController settingsController;
    private LightMap lightMap;

    public ApplicationContext(){
        settingsController = new SettingsControllerImpl();
        this.lightMap = new LightMap(10, 10);

    }

    public void run(){

    }
    public void stop(){

    }
    public SettingsController getSettingsController(){
        return this.settingsController;
    }

    public LightMap getLightMap() {
        return lightMap;
    }
}
