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
        creatures.forEach(Creature::update);
    }

}
