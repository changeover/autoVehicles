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
    private int directionX, directionY;
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
            int centerX = lightMap.getLightSourceX();
            int centerY = lightMap.getLightSourceY();
            if (creature.getCenterX() - centerX < 0) {
                directionX = 1;
            } else {
                directionX = -1;
            }
            if (creature.getCenterY() - centerY < 0) {
                directionY = 1;
            } else {
                directionY = -1;
            }
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
    public int getDirectionX(){
        return this.directionX;
    }
    public int getDirectionY(){
        return this.directionY;
    }
}
