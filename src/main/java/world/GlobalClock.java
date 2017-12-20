package world;

import java.util.Timer;
import java.util.TimerTask;

import application.ApplicationContext;

/**
 * This class models a timer that generates a global clock scheduling in witch frequency the map updates it self.
 * (fps)
 */
public class GlobalClock {

    private int frameCounter = 0;
    private int period;

    private LightMap lightMap;
    private ApplicationContext applicationContext;

    private Timer globalClock = new Timer();
    private TimerTask pulse = new TimerTask() {
        @Override
        public void run() {
            frameCounter++;
            System.out.println("Frame " + frameCounter);
            applicationContext.getLightMap().update();
        }
    };

    public GlobalClock(int fps, ApplicationContext appli) {
        this.period = 1 / fps * 1000;
        this.applicationContext=appli;
    }

    public void start() {
        globalClock.scheduleAtFixedRate(pulse, 0, period);
    }
}
