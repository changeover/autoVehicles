package vehicle;

import logic.Sensor;
import world.LightMap;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import grid.impl.LightDataLayer;


public class Eye implements Sensor {

    public enum sensorPosition {LEFTFRONT, LEFTBACK, RIGHTFRONT, RIGHTBACK}

    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    private sensorPosition position;

    private LightDataLayer lightmap;
    double intensity;

    public Eye(sensorPosition pos, LightDataLayer lightMap) {
        this.position = pos;
        this.lightmap = lightMap;
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
        switch (position) {
            case LEFTBACK:
                intensity = lightmap.getIntensity((int) currentPosX - 1, (int) currentPosY + 1);
                break;
            case LEFTFRONT:
                intensity = lightmap.getIntensity((int) currentPosX - 1, (int) currentPosY - 1);
                break;
            case RIGHTBACK:
                intensity = lightmap.getIntensity((int) currentPosX + 1, (int) currentPosY + 1);
                break;
            case RIGHTFRONT:
                intensity = lightmap.getIntensity((int) currentPosX + 1, (int) currentPosY - 1);
                break;
        }
        return intensity;
    }

    /**
     * Adds listeners observed values in this class
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    private void emitPropertyChange(String property, Object oldValue, Object newValue) {
        for (PropertyChangeListener l : listeners) {
            l.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
        }
    }

}
