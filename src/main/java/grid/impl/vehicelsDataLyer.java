package grid.impl;

import java.util.ArrayList;
import java.util.List;

import grid.GridWorldVehicle;
import javafx.geometry.Point2D;
/**
 * T needs to be changed to the Vehicle Objekt!!
 * @author Joel Zimmerli
 *
 */
public class vehicelsDataLyer<Vehicle> extends GridWorldFather<Vehicle> implements GridWorldVehicle<Vehicle, Vehicle>{
	List<Vehicle> vehicels;
	private Vehicle[][] values;
	public vehicelsDataLyer() {
		vehicels= new ArrayList<>();
	}
	
	@Override
	public List<Vehicle> getVehicles() {
		return vehicels;
	}
	
	@Override
	public String toString() {
		String out="";
		for (Vehicle[] row : values){
			for(Vehicle value : row){
				if(value!=null){
					out=out+"x\t|";
				}else{
					out+="0\t|";
				}
			}
			out+="\n";
		}
		return out;
	}

	@Override
	public void addVehicle(Vehicle vehicle, Point2D coordinates) {
		vehicels.add(vehicle);
		System.out.println("vehicelsDataLyer.addVehicle()"+vehicels);
		setValue(coordinates, vehicle);
	}

	@Override
	public void setData(Vehicle[][] values, String name) {
		super.values=values;
		super.name = name;
		super.fireDataChanged();
	}

}
