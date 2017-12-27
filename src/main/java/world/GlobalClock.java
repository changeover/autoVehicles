package world;

import javafx.animation.AnimationTimer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class models a timer that generates a global clock scheduling in witch frequency the map updates it self.
 * (fps)
 */
public class GlobalClock {


    private WorldPane worldPane;

    private AnimationTimer timer;


    public GlobalClock(WorldPane worldPane) {
        this.worldPane = worldPane;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                worldPane.update();
                System.out.println();

            }
        };


    }

    public void start() {
        timer.start();
    }
}
