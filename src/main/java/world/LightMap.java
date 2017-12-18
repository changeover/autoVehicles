package world;

import logic.Map;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greg on 16.12.2017.
 */
public class LightMap implements Map {

    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    double[][] gridData;

    public LightMap(int mapWidth, int mapHeight) {
        this.gridData = new double[mapWidth][mapHeight];
    }

    @Override
    public void placeLightSource(int xPos, int yPos) {

    }

    @Override
    public void placeCreature(int xPos, int yPos) {
        System.out.println("Place creature on map");
    }

    @Override
    public void update() {
        System.out.println("Map updated");
        emitPropertyChange("property", "oldValue", "new Value");
    }


    /**
     * Adds listeners to observed values in this class
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
