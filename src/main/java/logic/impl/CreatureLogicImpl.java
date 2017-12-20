package logic.impl;

import application.ApplicationContext;
import logic.CreatureLogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

public class CreatureLogicImpl implements CreatureLogic {
    private ApplicationContext applicationContext;
    private ArrayList<Thread> creatureThreadList;
    private ReentrantLock panelLock;
    private ReentrantLock lightMapLock;

    public CreatureLogicImpl(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
        creatureThreadList = new ArrayList<>();
        panelLock = new ReentrantLock();
        lightMapLock = new ReentrantLock();
        insertListerners();
    }
    private void insertListerners(){
        applicationContext.getSettingsController().addListener(() -> {
            if(getCreatureCount()< applicationContext.getSettingsController().getVehicleCount()) {
                for (int i = getCreatureCount(); i < applicationContext.getSettingsController().getVehicleCount(); i++) {
                    Thread creatureThread = new Thread(new vehicle.Creature(new int[1],applicationContext.getLightMap(),applicationContext.getMainPanel().getTopPane(),panelLock,lightMapLock));
                    appendCreatureThreadList(creatureThread);
                }
            }
            if(getCreatureCount()>applicationContext.getSettingsController().getVehicleCount()){
                int diff = getCreatureCount()-applicationContext.getSettingsController().getVehicleCount();
                removeThreads(diff );
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
        return this.creatureThreadList.size();
    }
    public void removeThreads (int count){
        Iterator<Thread> iter = creatureThreadList.iterator();
        while (iter.hasNext()) {
            Thread creature = iter.next();
            creature.interrupt();
            iter.remove();
        }
    }
    public ReentrantLock getPanelLock(){
        return this.panelLock;
    }
}
