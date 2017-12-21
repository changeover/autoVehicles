package vehicle;

import javafx.scene.shape.Circle;
import logic.SettingsControllerListener;

public interface Brain {
    void addListener(BrainListener brainListener);

    void computeNextPosition();

    void setCreature(Circle creature);

    double getPositionX();

    double getPositionY();

    void setIndex(int index);

}
