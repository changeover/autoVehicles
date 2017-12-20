package application;


import grid.MainPanel;
import logic.*;
import logic.impl.*;
import world.GlobalClock;
import world.LightMap;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ApplicationContext {
    private SettingsController settingsController;
    private LightMap lightMap;
    private MainPanel mainPanel;
    private CreatureLogic creatureLogic;
    private GlobalClock clock;


    public ApplicationContext(){
        settingsController = new SettingsControllerImpl();
        this.lightMap = new LightMap(10, 10);
        this.mainPanel = new MainPanel(this,3000, 2000);
        creatureLogic = new CreatureLogicImpl(this);
        clock = new GlobalClock(1, lightMap);


    }

    public MainPanel getMainPanel(){
        return mainPanel;
    }

    public void run(){
        settingsController.deactivateSlider();
        for(Thread creature:creatureLogic.getCreatureList()){
            creature.start();
        }
        clock.start();
    }
    public void stop(){
        settingsController.activateSlider();
        for(Thread creature:creatureLogic.getCreatureList()){
            creature.interrupt();
        }
        creatureLogic.getCreatureList().clear();
        mainPanel.getTopPane().getChildren().clear();
        clock.stop();
    }
    public SettingsController getSettingsController(){
        return this.settingsController;
    }

    public LightMap getLightMap() {
        return lightMap;
    }

}
