package application;


import grid.MainPanel;
import logic.*;
import logic.impl.*;
import world.LightMap;

import java.util.ArrayList;

public class ApplicationContext {
    private SettingsController settingsController;
    private LightMap lightMap;
    private ArrayList<Thread> creatureThreadList;
    private MainPanel mainPanel;




    public ApplicationContext(){
        settingsController = new SettingsControllerImpl();
        this.lightMap = new LightMap(10, 10);
        creatureThreadList = new ArrayList<>();
        this.mainPanel = new MainPanel(this,3000, 2000);
    }

    public MainPanel getMainPanel(){
        return mainPanel;
    }

    public void run(){
        settingsController.deactivateSlider();
        for(Thread creature:creatureThreadList){
            creature.start();
        }
    }
    public void stop(){
        settingsController.activateSlider();
        for(Thread creature:creatureThreadList){
            creature.interrupt();
        }
        creatureThreadList.clear();
        mainPanel.getTopPane().getChildren().clear();
    }
    public SettingsController getSettingsController(){
        return this.settingsController;
    }

    public LightMap getLightMap() {
        return lightMap;
    }
    public void appendCreatureThreadList(Thread creature){
        this.creatureThreadList.add(creature);
    }
    public int getCreatureCount(){
        return this.creatureThreadList.size();
    }
    public void removeThreads (int count){
        for(int i=0;i<count;i++){
            this.creatureThreadList.get(i).interrupt();
            this.creatureThreadList.remove(i);
        }
    }
}
