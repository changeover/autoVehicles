package vehicle;

import application.ApplicationContext;
import javafx.geometry.Point2D;
import logic.Creature;
import world.LightMap;

/**
 * /**
 * Creatures have sensors (eyes), actors (wheels) and a brain to link them. Based on information (next position)
 * given by the brain a creature can move to a certain position on its world (map).
 * Every creature lives in its own thread
 */

public class Creature implements Runnable {

    private Wheel wheel = new Wheel();

    private Eye eye;

    private Brain brain = new Brain();

    private ApplicationContext applicationContext;

    private Point2D position;

    public Creature(int[] spawnPosition, ApplicationContext aplli) {
        this.position = new Point2D(spawnPosition[0], spawnPosition[1]);
        this.applicationContext = aplli;
        eye=new Eye(new int[] {1,1}, applicationContext);
        wheel=new Wheel();
        this.brain.linkComponents(eye, wheel);

    }

    @Override
    public void run() {
    	try {
    		while(!Thread.interrupted()){
    			applicationContext.getVehicleGrid().setValue(position, this);
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}

    }

    private void update() {
        System.out.println("updated creature...");
        eye.measureLight();
    }


}
