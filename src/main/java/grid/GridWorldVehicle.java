package grid;
/**
 * This Interface extends the GridWorld Interface and add
 * methods used from vehicles only
 * @author Joel Zimmerli, Gregor von Gunten
 *
 * @param <Vehicle>  need to be created
 */

import java.util.List;

import javafx.geometry.Point2D;

public interface GridWorldVehicle<GridValue,Vehicle> extends GridWorld<GridValue> {
	/**
	 * Add a new vehicle to the list of vehicles and to the
	 * grid of vehicles
	 * @param vehicle
	 * @param koordinates
	 */
	void addVehicle(Vehicle vehicle, Point2D koordinates);
	/**
	 * create a new Vehicle on the given coordinates
	 * @param x
	 * @param y
	 */
	void createVehicle(int x, int y);
	
	/**
	 * Deletes all vehicle in the Layer
	 */
	void deleteVehicles();
	/**
	 * Return the list with all the Vehicles in the List
	 * @return
	 */
	List<Vehicle> getVehicles();

}
