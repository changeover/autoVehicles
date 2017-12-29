package vehicle;

import application.ApplicationContext;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import world.LightMap;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * /**
 * Creatures have sensors (eyes), actors (wheels) and a brain to link them. Based on information (next position)
 * given by the brain a creature can move to a certain position on its world (map).
 * Every creature lives in its own thread
 */

public class Creature extends Circle {

    double rotate = 0;

    private enum Location {TOPRIGHT, TOPLEFT, BOTTOMLEFT, BOTTOMRIGHT}
    private Location located;

    public enum Collision {NONE, RIGHT, LEFT, BOTTOM, TOP}

    private Collision collision;

    private Point2D currentVelocity = new Point2D(0, 0);

    public Boolean alive;

    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    private Wheel leftWheel = new Wheel(Wheel.WheelPosition.LEFT);
    private Wheel rightWheel = new Wheel(Wheel.WheelPosition.RIGHT);

    private Eye leftFrontEye;
    private Eye leftBackEye;
    private Eye rightFrontEye;
    private Eye rightBackEye;

    private Brain brain;

    private LightMap lightMap;

    public Creature(ApplicationContext applicationContext) {
        super(5, 5, 5, Color.HOTPINK);
        this.lightMap = applicationContext.getLightMap();
        this.leftFrontEye = new Eye(Eye.sensorPosition.LEFTFRONT, lightMap);
        this.leftBackEye = new Eye(Eye.sensorPosition.LEFTBACK, lightMap);
        this.rightFrontEye = new Eye(Eye.sensorPosition.RIGHTFRONT, lightMap);
        this.rightBackEye = new Eye(Eye.sensorPosition.RIGHTBACK, lightMap);
        this.lightMap.addPropertyChangeListener(e -> update());
        this.brain = new Brain(this);
        this.brain.linkComponents(leftFrontEye, leftBackEye, leftWheel);
        this.brain.linkComponents(rightFrontEye, rightBackEye, rightWheel);
        leftFrontEye.addPropertyChangeListener(e -> {
            this.collision = (Collision) e.getOldValue();
        });
        this.alive = true;

    }

    public void update() {
        emitPropertyChange("currentPosition", this.getTranslateX(), this.getTranslateY());

        if (collision != Collision.NONE) {
            //rotation
            currentVelocity = currentVelocity = new Point2D(currentVelocity.getX() *
                    Math.cos(Math.toRadians(180)) - currentVelocity.getY() * Math.sin(Math.toRadians(180)),
                    currentVelocity.getX() * Math.sin(Math.toRadians(180)) + currentVelocity.getY() *
                            Math.cos(Math.toRadians(180)));

        }

        Point2D leftVelocity = leftWheel.getVelocity();
        Point2D rightVelocity = rightWheel.getVelocity();
        Point2D result = leftVelocity.add(rightVelocity);

        double x = currentVelocity.getX();
        double y = currentVelocity.getY();

        double currentAngle = angleBetween(currentVelocity);
        double resultAngle = angleBetween(result);

        double angle = resultAngle - currentAngle;


        if (angle > 90 || angle < -90) {
            switch (located) {
                case TOPLEFT:
                    if (y > 0) {
                        rotate = -0.15;
                    } else {
                        rotate = +0.15;
                    }
                    break;
                case TOPRIGHT:
                    if (y > 0) {
                        rotate = +0.15;
                    } else {
                        rotate = -0.15;
                    }
                    break;
                case BOTTOMRIGHT:
                    if (y > 0) {
                        rotate = +0.15;
                    } else {
                        rotate = -0.15;
                    }
                    break;
                case BOTTOMLEFT:
                    if (y > 0) {
                        rotate = -0.15;
                    } else {
                        rotate = +0.15;
                    }
                    break;
            }

            currentVelocity = new Point2D(x * Math.cos(rotate) - y * Math.sin(rotate), x * Math.sin(rotate) + y * Math.cos(rotate));
        }


        this.setTranslateX(this.getTranslateX() + result.getX() + currentVelocity.getX());
        this.setTranslateY(this.getTranslateY() + result.getY() + currentVelocity.getY());

        currentVelocity = result.add(currentVelocity);

    }

    public Node getBody() {
        return this;
    }

    public double angleBetween(Point2D v) {

        Point2D reference = new Point2D(1, 0);

        //sector1
        if (v.getY() < 0 && v.getX() > 0) {
            located = Location.TOPLEFT;
            //System.out.print("go down right");
            return reference.angle(v);
        }
        //sector2
        else if (v.getY() < 0 && v.getX() < 0) {
            located = Location.TOPRIGHT;
            //System.out.print("go down left");
            return reference.angle(v);
        }
        //sector3
        else if (v.getY() > 0 && v.getX() < 0) {
            located = Location.BOTTOMRIGHT;
            //System.out.print("go up left");
            return reference.angle(v) + 90;
        }
        //sector3
        else if (v.getY() > 0 && v.getX() > 0) {
            located = Location.BOTTOMLEFT;
            //System.out.print("go up right");
            return reference.angle(v) + 180 + 90;
        }
        return 0;
    }

    public boolean isColloding(Creature other) {

        return getBoundsInParent().intersects(other.getBoundsInParent());
    }

    public void setAlive(boolean b) {
        alive = b;
    }

    public boolean isDead() {
        return !alive;
    }

    /**
     * Adds listeners observed values in this class
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    private void emitPropertyChange(String property, double xPos, double yPos) {
        for (PropertyChangeListener l : listeners) {
            l.propertyChange(new PropertyChangeEvent(this, property, xPos, yPos));
        }
    }
}
