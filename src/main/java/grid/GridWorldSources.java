package grid;

import java.util.Map;

import javafx.geometry.Point2D;

public interface GridWorldSources<T> extends GridWorld<T> {
	
	/**
	 * Sets a source at the given coordinates, which can probably float around
	 * @param koordiates
	 */
	void addSource(int[] koordiates, double value);
	/**
	 * Return the coordinate of the source with it's Value
	 * @return Map<Point2D, Double>
	 */
	Map<Point2D,Double> getSources();

}
