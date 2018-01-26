package logic;

/**
 * A sensor that measures the intensity of light in front of the vehicle (one-sided left or right).
 * This signal can be used to compute the linked actor's velocity vector.
 * 
 * @author Gregor von Gunten
 */
public interface Sensor {


    /**
     * Measure light intensity
     */
    double measureLight(double currentPosX, double currentPosY);

}
