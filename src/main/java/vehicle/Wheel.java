package vehicle;

import logic.Actor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greg on 16.12.2017.
 */
public class Wheel implements Actor {

    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    @Override
    public double[] getVelocity() {
        return new double[0];
    }


    @Override
    public void drive(double intensity) {
        System.out.println("calculate velocity vector");
    }

}

