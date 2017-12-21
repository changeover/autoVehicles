package world.impl;

import vehicle.impl.BrainImpl;
import world.LightMapListener;
import world.Map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Greg on 16.12.2017.
 */
public class LightMap implements Map {

    private final List<LightMapListener> listeners = new ArrayList<>();

    private int lightSourceX;
    private int lightSourceY;
    private ArrayList<Double> blockedX;
    private ArrayList<Double> blockedY;


    public LightMap(int mapWidth, int mapHeight) {
        this.blockedY = new ArrayList<>();
        this.blockedX = new ArrayList<>();
    }

    public void placeLightSource(int xPos, int yPos) {
        this.lightSourceX = xPos;
        this.lightSourceY = yPos;
    }

    @Override
    public void placeCreature(Double xPos, Double yPos,int index) {
        if(blockedX.size()>0) {
            blockedX.set(index, xPos);
        }
        if(blockedY.size()>0) {
            blockedY.set(index, yPos);
        }
    }

    @Override
    public int insertCreature(Double xPos, Double yPos) {
        int location;
        blockedY.add(yPos);
        blockedX.add(xPos);
        location = blockedX.size()-1;
        return location;
    }

    @Override
    public void update() {
        System.out.println("Map updated");
        emitPropertyChange();
    }


    /**
     * Adds listeners to observed values in this class
     *
     * @param listener
     */
    public void addPropertyChangeListener(LightMapListener listener) {
        listeners.add(listener);
    }

    private void emitPropertyChange() {
        Iterator<LightMapListener> iter = listeners.iterator();
        while(iter.hasNext()){
            iter.next().dataChanged();
        }
    }

    public int getLightSourceX(){
        return this.lightSourceX;
    }
    public int getLightSourceY(){
        return this.lightSourceY;
    }

    public ArrayList<Double> getBlockedX(){
        return this.blockedX;
    }
    public ArrayList<Double> getBlockedY(){
        return this.blockedY;
    }
}
