package vehicle;

import logic.Linker;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greg on 17.12.2017.
 */
public class Brain implements Linker {

    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    int[] nextPosition;

    @Override
    public double[] getNextPosition() {
        return new double[0];
    }

    @Override
    public void linkComponents(Eye eye, Wheel wheel) {
        eye.addPropertyChangeListener(e -> {
            wheel.drive(eye.getIntensity());
            computeNextPosition();
        });
    }

    @Override
    public void computeNextPosition() {
        System.out.println("compute next position");
        setNextPosition();
    }

    @Override
    public void setNextPosition() {
        emitPropertyChange("nextPosition", "oldValue", "newValue");
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
