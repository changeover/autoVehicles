package logic.vehicle;

import application.ApplicationContext;
import grid.impl.LightLayer;
import javafx.geometry.Point2D;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Creatures have sensors (eyes), actors (wheels) and a brain to link them. Based on information (next position)
 * given by the brain a creature can move to a certain position on its world (map).
 * Every creature lives in its own thread
 * 
 * @author Gregor von Gunten, Sahin Bayram, Andreas Ott, Cesar de Carmo
 */

public class Creature implements Runnable{

    private final List<PropertyChangeListener> listeners = new ArrayList<>();
    double rotate = 0;
    private Boolean isDead = false;

    private Location located;
    private Point2D currentVelocity = new Point2D(1, 1);
    private Point2D positionVehicle;
    private Wheel leftWheel;
    private Wheel rightWheel;
    private ApplicationContext applicationContext;
    private Eye leftFrontEye;
    private Eye leftBackEye;
    private Eye rightFrontEye;
    private Eye rightBackEye;
    private LightLayer.reachedBorder border = LightLayer.reachedBorder.NONE;
    private Brain brain;
    private LightLayer lightMap;
    private int speedAmplification;

    public Creature(ApplicationContext applicationContext, int x, int y) {
        this.speedAmplification = applicationContext.getSettingsController().getVehicleSpeed();
        this.positionVehicle  = new Point2D(x, y);
    	this.applicationContext=applicationContext;
        this.lightMap = applicationContext.getLightGrid();
        
        this.leftFrontEye = new Eye(Eye.sensorPosition.LEFTFRONT, lightMap);
        this.leftBackEye = new Eye(Eye.sensorPosition.LEFTBACK, lightMap);
        this.rightFrontEye = new Eye(Eye.sensorPosition.RIGHTFRONT, lightMap);
        this.rightBackEye = new Eye(Eye.sensorPosition.RIGHTBACK, lightMap);
        this.leftWheel = new Wheel(Wheel.WheelPosition.LEFT, speedAmplification);
        this.rightWheel = new Wheel(Wheel.WheelPosition.RIGHT, speedAmplification);

        this.brain = new Brain(this);
        this.brain.linkComponents(leftFrontEye, leftBackEye, leftWheel);
        this.brain.linkComponents(rightFrontEye, rightBackEye, rightWheel);
        applicationContext.getVehicleGrid().addVehicle(this, positionVehicle);
       
        leftFrontEye.addPropertyChangeListener(evt -> {
            if(leftFrontEye.getBorder() == LightLayer.reachedBorder.TOP ||
                    leftFrontEye.getBorder() == LightLayer.reachedBorder.LEFT){
                border = leftFrontEye.getBorder();
            }
        });
        leftBackEye.addPropertyChangeListener(evt -> {
            if(leftBackEye.getBorder() == LightLayer.reachedBorder.BOTTOM){
                border = leftBackEye.getBorder();
            }
        });
        rightFrontEye.addPropertyChangeListener(evt -> {
            if(rightFrontEye.getBorder() == LightLayer.reachedBorder.TOP ||
                    rightFrontEye.getBorder() == LightLayer.reachedBorder.RIGHT){
                border = rightFrontEye.getBorder();
            }
        } );
        rightBackEye.addPropertyChangeListener(evt -> {
            if(rightBackEye.getBorder() == LightLayer.reachedBorder.BOTTOM){
                border = rightBackEye.getBorder();
            }
        });
    }

    public void update() {
    	emitPropertyChange("currentPosition", positionVehicle.getX(), positionVehicle.getY());
        if(border == LightLayer.reachedBorder.NONE){

            Point2D leftVelocity = leftWheel.getVelocity();
            Point2D rightVelocity = rightWheel.getVelocity();
            Point2D vehicleVelocity = leftVelocity.add(rightVelocity);

            double x = currentVelocity.getX();
            double y = currentVelocity.getY();

            double currentAngle = angleBetween(currentVelocity);
            double resultAngle = angleBetween(vehicleVelocity);

            double angleToRotate = resultAngle - currentAngle;


            if (angleToRotate > 90 || angleToRotate < -90) {
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

                currentVelocity = new Point2D((x * Math.cos(rotate) - y * Math.sin(rotate)),( x * Math.sin(rotate) + y * Math.cos(rotate)));
            }

            positionVehicle = positionVehicle.add((vehicleVelocity.getX() + currentVelocity.getX()), (vehicleVelocity.getY() + currentVelocity.getY()));
            currentVelocity = vehicleVelocity.add(currentVelocity);
        }
        else {
            if(border == LightLayer.reachedBorder.TOP || border == LightLayer.reachedBorder.BOTTOM){
                currentVelocity = new Point2D((currentVelocity.getX() * Math.cos(3.1) - currentVelocity.getY() * Math.sin(3.1)), (currentVelocity.getX() * Math.sin(3.1) + currentVelocity.getY() * Math.cos(3.1)));
                positionVehicle = positionVehicle.add(currentVelocity);
                border = LightLayer.reachedBorder.NONE;
            }
            else if(border == LightLayer.reachedBorder.LEFT || border == LightLayer.reachedBorder.RIGHT){
                currentVelocity = new Point2D((currentVelocity.getX() * Math.cos(3.1) - currentVelocity.getY() * Math.sin(3.1)), (currentVelocity.getX() * Math.sin(3.1) + currentVelocity.getY() * Math.cos(3.1)));
                positionVehicle = positionVehicle.add(currentVelocity);
                border = LightLayer.reachedBorder.NONE;
            }
        }
    }

    public Point2D getPosition(){
    	return positionVehicle;
    }

    public double angleBetween(Point2D currentPoint) {

        Point2D referencePoint = new Point2D(1, 0);

        //sector1
        if (currentPoint.getY() < 0 && currentPoint.getX() > 0) {
            located = Location.TOPLEFT;
            //System.out.print("go down right");
            return referencePoint.angle(currentPoint);
        }
        //sector2
        else if (currentPoint.getY() < 0 && currentPoint.getX() < 0) {
            located = Location.TOPRIGHT;
            //System.out.print("go down left");
            return referencePoint.angle(currentPoint);
        }
        //sector3
        else if (currentPoint.getY() > 0 && currentPoint.getX() < 0) {
            located = Location.BOTTOMRIGHT;
            //System.out.print("go up left");
            return referencePoint.angle(currentPoint) + 90;
        }
        //sector4
        else if (currentPoint.getY() > 0 && currentPoint.getX() > 0) {
            located = Location.BOTTOMLEFT;
            //System.out.print("go up right");
            return referencePoint.angle(currentPoint) + 180 + 90;
        }
        return 0;
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

    public void killCreature() {
        isDead = true;
    }

	@Override
	public void run() {
		try {
            while (!isDead) {
                update();
    			applicationContext.getVehicleGrid().setValue(positionVehicle, this);

    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    private enum Location {TOPRIGHT, TOPLEFT, BOTTOMLEFT, BOTTOMRIGHT}
}
