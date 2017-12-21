package world;

import java.util.ArrayList;

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

    int getLightSourceX();

    int getLightSourceY();

    /**
     * Places a creature on the map
     *
     * @param xPos
     * @param yPos
     */
    void placeCreature(Double xPos, Double yPos, int index);

    /**
     * Update the map
     */
    int insertCreature(Double xPos, Double yPos);

    void update();

    ArrayList<Double> getBlockedX();

    ArrayList<Double> getBlockedY();
}
