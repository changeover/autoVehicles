package vehicle.impl;

import javafx.scene.shape.Circle;
import vehicle.Brain;
import vehicle.BrainListener;
import world.BlockedParts;
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
    private int index;
    private LightMap lightMap;
    private ReentrantLock lightmapLock;
    private BlockedParts blockedParts;
    private ReentrantLock blockedPartsLock;

    public BrainImpl(LightMap lightMap, ReentrantLock lightmapLock, BlockedParts blockedParts, ReentrantLock blockedPartsLock){
        lightmapLock.lock();
        try {
            lightMap.addPropertyChangeListener(() -> computeNextPosition());
        }
        finally {
            lightmapLock.unlock();
        }
        this.lightMap = lightMap;
        this.lightmapLock = lightmapLock;
        this.blockedParts = blockedParts;
        this.blockedPartsLock = blockedPartsLock;
    }

    public void computeNextPosition() {
        lightmapLock.lock();
        blockedPartsLock.lock();
        try {
            if (creature != null) {
                double directionX, directionY;
                int lightCenterX = lightMap.getLightSourceX();
                int lightCenterY = lightMap.getLightSourceY();
                directionX = lightCenterX - creature.getCenterX();
                directionY = lightCenterY - creature.getCenterY();
                double res = Math.sqrt(Math.pow(directionX, 2) + Math.pow(directionY, 2));
                directionX = directionX / res;
                directionY = directionY / res;
                newPositionX = creature.getCenterX() + directionX;
                newPositionY = creature.getCenterY() + directionY;
                boolean blockage;
                blockage = blockedParts.hasBlocked((int)newPositionX,(int)newPositionY);
                if (!blockage) {
                    //ugly af
                     //   lightMap.placeCreature(newPositionX, newPositionY, index);
                        fireDataChanged();
                }
            }
        }
        finally {
            blockedPartsLock.unlock();
            lightmapLock.unlock();
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
    public void setIndex(int index){
        this.index = index;
        this.index = this.index;
    }
}
