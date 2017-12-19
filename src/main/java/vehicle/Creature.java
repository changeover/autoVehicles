package vehicle;

import application.ApplicationContext;
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

    private int[] position;

    public Creature(int[] spawnPosition, ApplicationContext aplli) {
        this.position = spawnPosition;
        this.applicationContext = aplli;
        this.brain.linkComponents(eye, wheel);

    }

    @Override
    public void run() {

    }

    private void update() {
        System.out.println("updated creature...");
        eye.measureLight();
    }


}
