package logic.vehicle;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class links different thing together
 * 
 * @author Gregor von Gunten
 */
public class Brain {

    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    Creature creature;

    public Brain(Creature creature) {
        this.creature = creature;

    }

    public void linkComponents(Eye frontEye, Eye backEye, Wheel wheel) {
        creature.addPropertyChangeListener(evt -> wheel.drive(frontEye.measureLight(creature.getPosition().getX(), creature.getPosition().getY()),
                backEye.measureLight(creature.getPosition().getX(), creature.getPosition().getY())));
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
