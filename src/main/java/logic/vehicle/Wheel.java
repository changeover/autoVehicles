package logic.vehicle;

import javafx.geometry.Point2D;
import logic.Actor;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class simulates an Wheel on the Vehicle.
 * 
 * @author Gregor von Gunten, Andreas Ott
 */
public class Wheel implements Actor {

    private final List<PropertyChangeListener> listeners = new ArrayList<>();
    private WheelPosition position;
    private double magnitude;
    private double angle = 0;
    private Point2D velocity;
    private int speedAmplifier;

    public Wheel(WheelPosition position, int speedAmplifier) {

        this.position = position;
        this.speedAmplifier = speedAmplifier;
    }

    @Override
    public double getMagnitude() {

        return magnitude;
    }

    @Override
    public void drive(double frontIntensity, double backIntensity) {
        if (position == WheelPosition.RIGHT && frontIntensity <= backIntensity) {
            angle = 225 * Math.PI / 180;
            magnitude = frontIntensity;
        } else if (position == WheelPosition.RIGHT && frontIntensity > backIntensity) {
            angle = 135 * Math.PI / 180;
            magnitude = backIntensity;
        } else if (position == WheelPosition.LEFT && frontIntensity <= backIntensity) {
            angle = 315 * Math.PI / 180;
            magnitude = frontIntensity;
        } else if (position == WheelPosition.LEFT && frontIntensity > backIntensity) {
            angle = 45 * Math.PI / 180;
            magnitude = backIntensity;
        }
        double x = Math.cos(angle) * (magnitude) * speedAmplifier / 100;
        double y = Math.sin(angle) * (magnitude) * speedAmplifier / 100;
        velocity = new Point2D(x, y);
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public enum WheelPosition {LEFT, RIGHT}
}

