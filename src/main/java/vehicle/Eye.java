package vehicle;

import logic.Sensor;
import world.LightMap;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;


public class Eye implements Sensor {

    public enum sensorPosition {LEFTFRONT, LEFTBACK, RIGHTFRONT, RIGHTBACK}

    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    private sensorPosition position;

    private LightMap lightMap;
    double intensity;

    public Eye(sensorPosition pos, LightMap lightMap) {
        this.position = pos;
        this.lightMap = lightMap;
    }

    @Override
    public double getIntensity() {
        return 0;
    }

    @Override
    public void updatePosition(int xPos, int yPos) {

    }

    @Override
    public double measureLight(double currentPosX, double currentPosY) {
        Creature.Collision collision = checkForCollision((int) currentPosX, (int) currentPosY);
        if (collision == Creature.Collision.NONE) {
            switch (position) {
                case LEFTBACK:
                    intensity = lightMap.getIntensity((int) currentPosX - 1, (int) currentPosY + 1);
                    break;
                case LEFTFRONT:
                    intensity = lightMap.getIntensity((int) currentPosX - 1, (int) currentPosY - 1);
                    break;
                case RIGHTBACK:
                    intensity = lightMap.getIntensity((int) currentPosX + 1, (int) currentPosY + 1);
                    break;
                case RIGHTFRONT:
                    intensity = lightMap.getIntensity((int) currentPosX + 1, (int) currentPosY - 1);
                    break;
            }
            return intensity;
        } else
            return 0;
    }


    private Creature.Collision checkForCollision(int currentPosX, int currentPosY) {
        Creature.Collision collision = Creature.Collision.NONE;
        int xBorder = this.lightMap.getGridData().length;
        int yBorder = this.lightMap.getGridData()[0].length;
        if (currentPosX - 1 < 0) {
            collision = Creature.Collision.RIGHT;
        }
        if (currentPosX + 1 > xBorder) {
            collision = Creature.Collision.LEFT;
        }
        if (currentPosY - 1 < 0) {
            collision = Creature.Collision.TOP;
        }
        if (currentPosY + 1 > yBorder) {
            collision = Creature.Collision.BOTTOM;
        }
        emitPropertyChange("collison", collision);
        return collision;
    }

    /**
     * Adds listeners observed values in this class
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    private void emitPropertyChange(String property, Creature.Collision collision) {
        for (PropertyChangeListener l : listeners) {
            l.propertyChange(new PropertyChangeEvent(this, property, collision, null));
        }
    }

}
