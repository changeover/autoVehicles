package world;

import world.impl.LightMap;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class models a timer that generates a global clock scheduling in witch frequency the map updates it self.
 * (fps)
 */
public class GlobalClock {

    private int frameCounter = 0;
    private int period;

    private LightMap lightMap;
    private ReentrantLock lightMapLock;
    private Timer globalClock = new Timer();
    private TimerTask pulse = new TimerTask() {
        @Override
        public void run() {
            timerRun();
        }
    };

    public GlobalClock(int fps, LightMap lightMap,ReentrantLock lightMapLock) {
        this.period = (1 / fps) * 1000;
        this.lightMap = lightMap;
        this.lightMapLock = lightMapLock;
    }

    public void start() {
        globalClock.scheduleAtFixedRate(pulse, 0, 16);
    }

    public void stop(){
        pulse.cancel();
        globalClock.cancel();
        globalClock = new Timer();
        pulse = new TimerTask() {
            @Override
            public void run() {
                timerRun();
            }
        };
    }
    private void timerRun(){
        frameCounter++;
        System.out.println("Frame " + frameCounter);
        lightMapLock.lock();
        try {
            lightMap.update();
        }
        finally {
            lightMapLock.unlock();
        }
    }
}
