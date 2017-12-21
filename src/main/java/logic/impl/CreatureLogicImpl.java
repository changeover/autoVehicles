package logic.impl;

import application.ApplicationContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import logic.CreatureLogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

public class CreatureLogicImpl implements CreatureLogic {
    private ApplicationContext applicationContext;
    private ArrayList<Thread> creatureThreadList;
    private ArrayList<Circle> creatureList;
    private ReentrantLock panelLock;
    private ReentrantLock lightMapLock;

    public CreatureLogicImpl(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
        creatureThreadList = new ArrayList<>();
        creatureList = new ArrayList<>();
        panelLock = applicationContext.getPanelLock();
        lightMapLock = applicationContext.getLightMapLock();
        insertListerners();
    }
    private void insertListerners(){
        applicationContext.getSettingsController().addListener(() -> {
            if(getCreatureCount()< applicationContext.getSettingsController().getVehicleCount()) {
                for (int i = getCreatureCount(); i < applicationContext.getSettingsController().getVehicleCount(); i++) {
                    insertCreatures();
                }
            }
            if(getCreatureCount()>applicationContext.getSettingsController().getVehicleCount()){
                int newCount = applicationContext.getSettingsController().getVehicleCount();
                removeThreads(newCount);
            }
        });
    }

    public ArrayList<Thread> getCreatureList() {
        return creatureThreadList;
    }
    public void appendCreatureThreadList(Thread creature){
        this.creatureThreadList.add(creature);
    }
    public int getCreatureCount(){
        return this.creatureList.size();
    }
    public void removeThreads (int newCount){
        Iterator<Thread> iter = creatureThreadList.iterator();
        while (iter.hasNext()) {
            Thread creature = iter.next();
            creature.interrupt();
            iter.remove();
        }
        creatureList.clear();
        applicationContext.getLightMap().getBlockedX().clear();
        applicationContext.getLightMap().getBlockedY().clear();
        for(int i = getCreatureCount();i<applicationContext.getSettingsController().getVehicleCount();i++){
            insertCreatures();
        }

    }

    private void insertCreatures(){
        Circle creature = new Circle();
        Double randX =  (Math.random() * 1400);
        Double randY =  (Math.random() * 800);
        creature.setCenterX(randX);
        creature.setCenterY(randY);
        creature.setRadius(10);
        creature.setFill(Color.HOTPINK);
        int index = applicationContext.getLightMap().insertCreature(randX, randY);
        Thread creatureThread = new Thread(new vehicle.Creature(applicationContext.getLightMap(),panelLock,lightMapLock,creature, index));
        applicationContext.getMainPanel().getTopPane().getChildren().add(creature);
        appendCreatureThreadList(creatureThread);
        creatureList.add(creature);
        applicationContext.getLightMap().insertCreature(randX,randY);
    }
}
