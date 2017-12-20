package vehicle;

import logic.Sensor;
import world.LightMap;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import application.ApplicationContext;

//url = https://github.com/changeover/autoVehicles.git
//url=ssh://git@github.com:changeover/autoVehicles.git

public class Eye implements Sensor {


    private final List<PropertyChangeListener> listeners = new ArrayList<>();
    private final ApplicationContext applicationCOntext;
    private int[] position;

    public Eye(int[] position, ApplicationContext appli) {
        this.position = position;
        this.applicationCOntext=appli;
    }

    @Override
    public double getIntensity() {
        return 0;
    }

    @Override
    public void updatePosition(int xPos, int yPos) {

    }

    @Override
    public void measureLight() {
        System.out.println("measure light intensity");
        emitPropertyChange("intensity", "oldValue", "newValue");
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
