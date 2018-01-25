package grid;

import javafx.geometry.Point2D;

/**
 * This interface models a two-dimensional grid of Objects.
 * The grid has a specific width and height.
 * The grid has a name, to determine it's content
 * if the data is changed over a setData method then a 
 * dataChanged event is sent to registered observers (listeners)
 * This Interface needs to be Modified to fit to different layers types
 * @author Joel Zimmerli
 *
 */
public interface GridWorld<GridValue> {
	
	
	/**
	 * Load data into the grid and sets it's name
	 * @param values a two-dimensional array of integer values
	 * @param name a name that describe this layer in the world
	 */
	void setData(GridValue[][] values, String name);
	/**
	 * Allows the set a specific field in the grid
	 * @param coordinates place where the value should be changed
	 * @param value value of the change
	 */
	void setValue(Point2D coordinates, GridValue value);
	
	/**
	 * Returns the width of the grid
	 * @return
	 */
	int getWidthGrid();
	/**
	 * Return the height of the grid
	 * @return
	 */
	int getHeightGrid();
	/**
	 * Returns the data Value at the specific position.
	 * @param x,y coordinates of the searched Value
	 * @return data Value of the specific field
	 */
	GridValue getCellValue(int x, int y);
	/**
	 * Returns the name of the layer
	 * @return
	 */
	String getName();
	/**
	 * Register an observer that is notified if this specific Layer
	 * is changed.
	 * @param listener
	 */
	void addListener(GridWorldListener listener);
	/**
	 * set All value back
	 */
	void resetValues();

}
