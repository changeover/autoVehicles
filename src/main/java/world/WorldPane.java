package world;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import vehicle.Creature;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greg on 24.12.2017.
 */
public class WorldPane extends Pane {

    private List<Creature> creatures = new ArrayList<>();

    public void placeCreature(Creature creature, double x, double y) {
        creatures.add(creature);
        creature.setTranslateX(x);
        creature.setTranslateY(y);
        this.getChildren().add(creature);

    }

    public void update() {

        for (Creature cx : creatures) {
            for (Creature cy : creatures) {
                if (!cx.equals(cy) && cx.isColloding(cy)) {
                    //cx.setAlive(false);
                    //cy.setAlive(false);
                    //getChildren().removeAll(cx,cy);
                    cx.momentumConservation(cy.getCurrentVelocity());
                    cy.momentumConservation(cx.getCurrentVelocity());
                }
            }

        }

        creatures.removeIf(Creature::isDead);
        creatures.forEach(Creature::update);


    }

}
