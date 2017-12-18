package world;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class models a timer that generates a global clock scheduling in witch frequency the map updates it self.
 * (fps)
 */
public class GlobalClock {

    private int frameCounter = 0;
    private int period;

    private LightMap lightMap;

    private Timer globalClock = new Timer();
    private TimerTask pulse = new TimerTask() {
        @Override
        public void run() {
            frameCounter++;
            System.out.println("Frame " + frameCounter);
            lightMap.update();
        }
    };

    public GlobalClock(int fps, LightMap lightMap) {
        this.period = 1 / fps * 1000;
        this.lightMap = lightMap;
    }

    public void start() {
        globalClock.scheduleAtFixedRate(pulse, 0, period);
    }
}
