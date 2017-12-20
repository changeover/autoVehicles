package vehicle.impl;

import javafx.scene.shape.Circle;
import vehicle.Brain;
import vehicle.BrainListener;
import world.impl.LightMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Greg on 17.12.2017.
 */
public class BrainImpl implements Brain {

    private final List<BrainListener> listeners = new ArrayList<>();
    private Circle creature;
    private double newPositionX, newPositionY;
    private LightMap lightMap;

    public BrainImpl(LightMap lightMap, ReentrantLock lightmapLock){
        lightmapLock.lock();
        try {
            lightMap.addPropertyChangeListener(() -> computeNextPosition());
        }
        finally {
            lightmapLock.unlock();
        }
        this.lightMap = lightMap;
    }

    public void computeNextPosition() {
        if(creature != null) {
            int directionX, directionY;
            int LightCenterX = lightMap.getLightSourceX();
            int LightCenterY = lightMap.getLightSourceY();
            if (creature.getCenterX() - LightCenterX < 0) {
                directionX = 1;
            } else {
                directionX = -1;
            }
            if (creature.getCenterY() - LightCenterY < 0) {
                directionY = 1;
            } else {
                directionY = -1;
            }
            newPositionX = creature.getCenterX() + directionX;
            newPositionY = creature.getCenterY() + directionY;
            fireDataChanged();
        }
    }

    public void setCreature(Circle creature) {
        this.creature = creature;
    }

    private void fireDataChanged() {
        Iterator<BrainListener> iter = listeners.iterator();
        while (iter.hasNext()) {
            iter.next().dataChanged();
        }
    }

    public void addListener(BrainListener brainListener) {
        listeners.add(brainListener);
    }
    public double getPositionX(){
        return this.newPositionX;
    }
    public double getPositionY(){
        return this.newPositionY;
    }
}
