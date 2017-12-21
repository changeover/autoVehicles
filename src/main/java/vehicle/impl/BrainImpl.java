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
    private int index;
    private LightMap lightMap;
    private ReentrantLock lightmapLock;

    public BrainImpl(LightMap lightMap, ReentrantLock lightmapLock){
        lightmapLock.lock();
        try {
            lightMap.addPropertyChangeListener(() -> computeNextPosition());
        }
        finally {
            lightmapLock.unlock();
        }
        this.lightMap = lightMap;
        this.lightmapLock = lightmapLock;
    }

    public void computeNextPosition() {
        lightmapLock.lock();
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
                ArrayList<Double> blockedX = lightMap.getBlockedX();
                ArrayList<Double> blockedY = lightMap.getBlockedY();
                boolean blockage = false;
                for(int i=0; i<blockedX.size();i++){
                    blockage = false;
                    if(i!=index) {
                        for (int ii = -10; ii <= 10; ii++) {
                            if ((int) newPositionX == blockedX.get(i) + ii) {
                                blockage = true;
                            }
                            if ((int) newPositionY == blockedY.get(i) + ii) {
                                blockage = true;
                            }
                        }
                    }
                }
                if (!blockage) {
                    //ugly af
                    if(lightMap.getBlockedX().size()>index) {
                     //   lightMap.placeCreature(newPositionX, newPositionY, index);
                        fireDataChanged();
                    }
                }
            }
        }
        finally {
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
