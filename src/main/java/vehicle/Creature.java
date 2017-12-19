package vehicle;

import grid.MainPanel;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import world.LightMap;

import java.util.Stack;

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
    private Brain brain = new Brain();
    private LightMap lightMap;
    private int[] position;
    Circle creature;

    public Creature(int[] spawnPosition, LightMap lightMap, Pane topPane) {
        this.position = spawnPosition;
        this.lightMap = lightMap;
        this.eye = new Eye(position, lightMap);
        this.lightMap.addPropertyChangeListener(e -> update());
        this.brain.linkComponents(eye, wheel);
        //this.brain.addPropertyChangeListener(e -> lightMap.placeCreature(0, 0));
        this.topPane = topPane;

    }

    @Override
    public void run() {
        Platform.runLater(() ->
        {
            creature = new Circle();
            int randX = (int)(Math.random() * 3000);
            int randY = (int)(Math.random() * 2000);
            creature.setCenterX(randX);
            creature.setCenterY(randY);
            creature.setRadius(10);
            creature.setFill(Color.HOTPINK);
            topPane.getChildren().add(creature);
        });
    }

    private void update() {
        if(creature != null) {
            creature.setCenterX(creature.getCenterX() + 1);
            creature.setCenterY(creature.getCenterY() + 2);
            System.out.println("updated creature...");
            eye.measureLight();
        }
    }

}
