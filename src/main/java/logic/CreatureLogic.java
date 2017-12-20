package logic;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Creatures have sensors (eyes), actors (wheels) and a brain to link them. Based on information (next position)
 * given by the brain a creature can move to a certain position on its world (map).
 * Every creature lives in its own thread
 */
public interface CreatureLogic {
    ArrayList<Thread> getCreatureList();

    void appendCreatureThreadList(Thread creature);

    int getCreatureCount();

    void removeThreads (int count);

    ReentrantLock getPanelLock();
}
