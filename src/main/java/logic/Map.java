package logic;

/**
 * The Map contains a two-dimensional array that represents one layer  of the world the creatures are living.
 */
public interface Map {

    /**
     * Places a source of light on the map and fills its elements with values based on some kind of distribution.
     *
     * @param xPos
     * @param yPos
     */
    void placeLightSource(int xPos, int yPos);

    /**
     * Places a creature on the map
     *
     * @param xPos
     * @param yPos
     */
    void placeCreature(int xPos, int yPos);

    double getIntensity(int xPos, int yPos);

    /**
     * Update the map
     */
    void update();

}
