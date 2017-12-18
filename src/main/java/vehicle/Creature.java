package vehicle;

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

    private LightMap lightMap;

    private int[] position;

    public Creature(int[] spawnPosition, LightMap lightMap) {
        this.position = spawnPosition;
        this.lightMap = lightMap;
        this.eye = new Eye(position, lightMap);
        this.lightMap.addPropertyChangeListener(e -> update());
        this.brain.linkComponents(eye, wheel);
        this.brain.addPropertyChangeListener(e -> lightMap.placeCreature(0, 0));

    }

    @Override
    public void run() {

    }

    private void update() {
        System.out.println("updated creature...");
        eye.measureLight();
    }


}
