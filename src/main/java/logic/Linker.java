package logic;

import vehicle.Eye;
import vehicle.Wheel;

/**
 * Links sensors with their actors and calculates the vehicle's position for the next frame.
 * This could be achieved by multiplying the velocity vector of each actor
 */
public interface Linker {

    /**
     * Gets x and y position for the next frame.
     *
     * @return
     */
    double[] getNextPosition();

    /**
     * link a sensor with an actor
     *
     * @param eye
     * @param wheel
     */
    void linkComponents(Eye eye, Wheel wheel);

    /**
     * Calculates the creature's new position on the map based on the velocity vector of each actor
     */
    void computeNextPosition();

    /**
     * Sets x and y values for the creatures next position
     */
    void setNextPosition();
}
