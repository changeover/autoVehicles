package logic;

/**
 * A sensor that measures the intensity of light in front of the vehicle (one-sided left or right).
 * This signal can be used to compute the linked actor's velocity vector.
 */
public interface Sensor {


    /**
     * Returns the measured light intensity
     *
     * @return light intensity
     */
    double getIntensity();

    /**
     * Updates the position of this sensor
     */
    void updatePosition(int xPos, int yPos);

    /**
     * Measure light intensity
     */
    void measureLight();

}
