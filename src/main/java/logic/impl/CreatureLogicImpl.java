package logic.impl;

import application.ApplicationContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.CreatureLogic;
import vehicle.Brain;
import vehicle.impl.BrainImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

public class CreatureLogicImpl implements CreatureLogic {
    private ApplicationContext applicationContext;
    private ArrayList<Thread> creatureThreadList;
    private ArrayList<Circle> creatureList;
    private ArrayList<Brain> brainList;
    private ReentrantLock panelLock;
    private ReentrantLock lightMapLock;

    public CreatureLogicImpl(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
        creatureThreadList = new ArrayList<>();
        creatureList = new ArrayList<>();
        brainList = new ArrayList<>();
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
                Thread thread = iter.next();
                thread.interrupt();
                thread = null;
                iter.remove();
            }
        Iterator<Circle> creatureIt = creatureList.iterator();
        while (creatureIt.hasNext()){
            Circle circle = creatureIt.next();
            applicationContext.getMainPanel().getTopPane().getChildren().remove(circle);
            circle = null;
            creatureIt.remove();
        }
        Iterator<Brain> brainIt = brainList.iterator();
        while (brainIt.hasNext()){
            Brain brain = brainIt.next();
            brain = null;
            brainIt.remove();
        }
        applicationContext.getLightMap().getBlockedX().clear();
        applicationContext.getLightMap().getBlockedY().clear();
        System.gc();
        for(int i = getCreatureCount();i<applicationContext.getSettingsController().getVehicleCount();i++){
            insertCreatures();
        }

    }

    private void insertCreatures(){
        Circle creature = new Circle();
        int creatureSize = 10;
        creature.setRadius(creatureSize);
        boolean blocked = true;
        double randX = 0.0,randY = 0.0;
        double x,y;
        double width = applicationContext.getMainPanel().getTopPane().getWidth();
        double height = applicationContext.getMainPanel().getTopPane().getHeight();
        while(blocked){
            randX =  Math.random() * (width-creatureSize);
            randY =  Math.random() * (height-creatureSize);
            if(applicationContext.getBlockedParts().getBlockedX().size()>0) {
                blocked = applicationContext.getBlockedParts().hasBlocked((int)randX,(int)randY);
            }
            else{
                blocked = false;
            }
        }
        creature.setFill(Color.HOTPINK);
        creature.setCenterY(randY);
        creature.setCenterX(randX);
        int index = applicationContext.getLightMap().insertCreature(randX, randY);
        Brain brain = new BrainImpl(applicationContext.getLightMap(), lightMapLock,applicationContext.getBlockedParts(),applicationContext.getBlockedPartsLock());
        brainList.add(brain);
        brain.setCreature(creature);
        brain.setIndex(index);
        Thread creatureThread = new Thread(new vehicle.Creature(panelLock,creature,brain));
        applicationContext.getMainPanel().getTopPane().getChildren().add(creature);
        appendCreatureThreadList(creatureThread);
        creatureList.add(creature);
    }
}
