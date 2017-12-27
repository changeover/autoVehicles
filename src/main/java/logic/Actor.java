package logic;

import javafx.geometry.Point2D;

/**
 * This interface models an actor of a vehicle. Based on signals emit by its linked sensors the actor can calculate
 * a velocity vector. (the stronger the signal the bigger is the magnitude of the computed vector
 */
public interface Actor {

    /**
     * Returns a vector that represents the speed (magnitude and angle or  x and y position)
     *
     * @return velocity vector
     */
    double getMagnitude();

    /**
     * Computes the current velocity vector (speed and angle) based on its geometry and measured light intensity.
     *
     * @param frontIntensity
     * @param backIntensity
     */
    void drive(double frontIntensity, double backIntensity);
}
