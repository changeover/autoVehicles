package application;


import grid.MainPanel;
import logic.*;
import logic.impl.*;
import sun.applet.Main;
import world.LightMap;

import java.awt.*;

public class ApplicationContext {
    private SettingsController settingsController;
    private LightMap lightMap;
    private MainPanel mainPanel;




    public ApplicationContext(){
        settingsController = new SettingsControllerImpl();
        this.lightMap = new LightMap(10, 10);
        this.mainPanel = new MainPanel(this,3000, 2000);
    }

    public MainPanel getMainPanel(){
        return mainPanel;
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
