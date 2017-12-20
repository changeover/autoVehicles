package vehicle;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import vehicle.impl.BrainImpl;
import world.impl.LightMap;


import java.util.concurrent.locks.ReentrantLock;

/**
 * /**
 * Creatures have sensors (eyes), actors (wheels) and a brain to link them. Based on information (next position)
 * given by the brain a creature can move to a certain position on its world (map).
 * Every creature lives in its own thread
 */

public class Creature implements Runnable {
    private Wheel wheel = new Wheel();
    private Pane topPane;
    private Eye eye;
    private BrainImpl brain;
    private int[] position;
    private Circle creature;
    private ReentrantLock panelLock;

    public Creature(int[] spawnPosition, LightMap lightMap, Pane topPane, ReentrantLock panelLock, ReentrantLock lightMapLock) {
        this.position = spawnPosition;
        this.topPane = topPane;
        this.panelLock = panelLock;
        brain = new BrainImpl(lightMap, lightMapLock);
        brain.addListener(()->update(brain.getDirectionX(),brain.getDirectionY()));
    }

    @Override
    public void run() {
        panelLock.lock();
        try {
            Platform.runLater(() ->
            {
                creature = new Circle();
                brain.setCreature(creature);
                int randX = (int) (Math.random() * 1400);
                int randY = (int) (Math.random() * 800);
                creature.setCenterX(randX);
                creature.setCenterY(randY);
                creature.setRadius(10);
                creature.setFill(Color.HOTPINK);
                topPane.getChildren().add(creature);
            });
        }
        finally {
            panelLock.unlock();
        }
    }

    private void update(int directionX, int directionY) {
        panelLock.lock();
        try {
            if (creature != null) {
                Platform.runLater(()->{
                    creature.setCenterX(creature.getCenterX() + directionX);
                    creature.setCenterY(creature.getCenterY() + directionY);
                });
            }
        }
        finally {
            panelLock.unlock();
        }
    }

}
