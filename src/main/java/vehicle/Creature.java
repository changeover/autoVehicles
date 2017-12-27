package vehicle;

import application.ApplicationContext;
import javafx.geometry.Point2D;

/**
 * /**
 * Creatures have sensors (eyes), actors (wheels) and a brain to link them. Based on information (next position)
 * given by the brain a creature can move to a certain position on its world (map).
 * Every creature lives in its own thread
 */

public class Creature implements Runnable {

    private ApplicationContext applicationContext;

    private Point2D position;

    public Creature(int[] spawnPosition, ApplicationContext aplli) {
        this.position = new Point2D(spawnPosition[0], spawnPosition[1]);
        this.applicationContext = aplli;
        aplli.getVehicleGrid().addVehicle(this, position);
        
    }
    
    public Point2D getPosition(){
    	return position;
    }

    @Override
    public void run() {
    	try {
    		while(true){
    			System.out.println("Creature.run()");
    			applicationContext.getVehicleGrid().setValue(position, this);
    			position = position.add(Math.round(Math.random() * 1)   , Math.round(Math.random() * 1.5)  );

    		}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

    }


}
