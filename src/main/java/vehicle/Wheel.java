package vehicle;

import javafx.geometry.Point2D;
import logic.Actor;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greg on 16.12.2017.
 */
public class Wheel implements Actor {

    public enum WheelPosition {LEFT, RIGHT}

    private WheelPosition position;

    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    private double magnitude;
    private double angle = 0;
    private Point2D velocity;

    public Wheel(WheelPosition position) {
        this.position = position;
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
        double x = Math.cos(angle) * (magnitude);
        double y = Math.sin(angle) * (magnitude);
        velocity = new Point2D(x, y);
    }

    public Point2D getVelocity() {
        return velocity;
    }
}

