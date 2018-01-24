package vehicle;

import logic.Sensor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import grid.impl.LightLayer;


public class Eye implements Sensor {

    public enum sensorPosition {LEFTFRONT, LEFTBACK, RIGHTFRONT, RIGHTBACK}

    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    private sensorPosition position;

    private LightLayer lightmap;
    double intensity;

    private LightLayer.reachedBorder border;

    public LightLayer.reachedBorder getBorder() {
        return border;
    }

    public Eye(sensorPosition pos, LightLayer lightMap) {
        this.position = pos;
        this.lightmap = lightMap;
    }


    @Override
    public double measureLight(double currentPosX, double currentPosY) {
        border = lightmap.checkForCollision((int)currentPosX,(int)currentPosY);
        emitPropertyChange("border", null, border);
        if(border == LightLayer.reachedBorder.NONE){
            switch (position) {
                case LEFTBACK:
                    intensity = lightmap.getValue((int) currentPosX - 1, (int) currentPosY + 1);
                    break;
                case LEFTFRONT:
                    intensity = lightmap.getValue((int) currentPosX - 1, (int) currentPosY - 1);
                    break;
                case RIGHTBACK:
                    intensity = lightmap.getValue((int) currentPosX + 1, (int) currentPosY + 1);
                    break;
                case RIGHTFRONT:
                    intensity = lightmap.getValue((int) currentPosX + 1, (int) currentPosY - 1);
                    break;

            }
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
