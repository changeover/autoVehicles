package application;


import grid.MainPanel;
import logic.*;
import logic.impl.*;
import world.GlobalClock;
import world.impl.LightMap;

import java.util.concurrent.locks.ReentrantLock;

public class ApplicationContext {
    private SettingsController settingsController;
    private LightMap lightMap;
    private MainPanel mainPanel;
    private CreatureLogic creatureLogic;
    private GlobalClock clock;
    private ReentrantLock panelLock;
    private ReentrantLock lightMapLock;


    public ApplicationContext(){
        settingsController = new SettingsControllerImpl();
        this.lightMap = new LightMap(10, 10);
        this.mainPanel = new MainPanel(this,3000, 2000);
        panelLock = new ReentrantLock();
        lightMapLock = new ReentrantLock();
        creatureLogic = new CreatureLogicImpl(this);
        clock = new GlobalClock(1, lightMap,lightMapLock);


    }

    public MainPanel getMainPanel(){
        return mainPanel;
    }

    public void run(){
        settingsController.deactivateSlider();
        for(Thread creature:creatureLogic.getCreatureList()){
            creature.start();
            try {
                creature.join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
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

    public ReentrantLock getLightMapLock() {
        return lightMapLock;
    }

    public ReentrantLock getPanelLock() {
        return panelLock;
    }
}
