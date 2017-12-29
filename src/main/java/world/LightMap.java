package world;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import logic.Map;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Greg on 16.12.2017.
 */
public class LightMap implements Map {

    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    private double[][] gridData;
    private Image backGround;
    private WritableImage writableImage;

    public LightMap(int mapWidth, int mapHeight) {
        this.gridData = new double[mapWidth][mapHeight];
        writableImage = new WritableImage(mapWidth, mapHeight);
        placeLightSource(mapWidth, mapHeight);
    }

    @Override
    public void placeLightSource(int width, int height) {
        int windowedValue;
        double max = 0;
        double min = 0;
        double p = 0.1;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int xPos = x - width / 2 + 1;
                int yPos = y - height / 2 + 1;
                gridData[x][y] = 1 / (2 * 6.1 * Math.sqrt(1 - p)) *
                        Math.exp(-1 / (2 * (1 - Math.sqrt(1 - Math.pow(p, 2))) *
                                (xPos * xPos + 2 * p * xPos * yPos + yPos * yPos)));

                max = (max < gridData[x][y]) ? gridData[x][y] : max;
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double value = (gridData[x][y] / max * 255);
                windowedValue = 255 - (int) value;
                writableImage.getPixelWriter().setColor(x, y, Color.rgb(windowedValue, windowedValue, 70));

            }
        }

        backGround = writableImage;

        //System.out.print("\r"+Arrays.deepToString(gridData).replace("], ", "]\n"));
        //System.out.println(max+ " : "+min);
    }

    public Image getBackGround() {
        return backGround;
    }

    public double[][] getGridData() {
        return gridData;
    }

    @Override
    public void placeCreature(int xPos, int yPos) {
    }

    @Override
    public double getIntensity(int xPos, int yPos) {
        return gridData[xPos][yPos];
    }

    @Override
    public void update() {
        emitPropertyChange("property", "oldValue", "new Value");
    }


    /**
     * Adds listeners to observed values in this class
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    private void emitPropertyChange(String property, Object oldValue, Object newValue) {
        for (PropertyChangeListener l : listeners) {
            l.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
        }
    }


}
