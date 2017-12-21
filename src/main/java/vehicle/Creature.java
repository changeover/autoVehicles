package vehicle;

import javafx.application.Platform;
import javafx.scene.effect.Light;
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
    private Eye eye;
    private Brain brain;
    private Circle creature;
    private ReentrantLock panelLock;

    public Creature(ReentrantLock panelLock, Circle creature, Brain brain) {
        this.panelLock = panelLock;
        this.brain = brain;
        this.creature = creature;
        brain.addListener(()->update(brain.getPositionX(),brain.getPositionY()));
    }

    @Override
    public void run() {

    }

    private void update(double positionX, double positionY) {
        panelLock.lock();
        try {
            if (creature != null) {
                Platform.runLater(()->{
                    creature.setCenterX(positionX);
                    creature.setCenterY(positionY);
                });
            }
        }
        finally {
            panelLock.unlock();
        }
    }

}
