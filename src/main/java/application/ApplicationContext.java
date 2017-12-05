package application;


import logic.*;
import logic.impl.*;

public class ApplicationContext {
    SettingsController settingsController;
    public ApplicationContext(){
        settingsController = new SettingsControllerImpl();
    }
    public void run(){

    }
    public void stop(){

    }
    public SettingsController getSettingsController(){
        return this.settingsController;
    }
}
