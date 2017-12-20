package world.impl;

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

    double[][] gridData;
    int lightSourceX;
    int lightSourceY;

    public LightMap(int mapWidth, int mapHeight) {
        this.gridData = new double[mapWidth][mapHeight];
    }

    public void placeLightSource(int xPos, int yPos) {
        this.lightSourceX = xPos;
        this.lightSourceY = yPos;
    }

    @Override
    public void placeCreature(int xPos, int yPos) {
        System.out.println("Place creature on map");
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
}
