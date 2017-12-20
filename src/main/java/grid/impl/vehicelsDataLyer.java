package grid.impl;

import java.util.ArrayList;
import java.util.List;

import grid.GridWorldVehicle;
import javafx.geometry.Point2D;
import logic.Creature;
/**
 * T needs to be changed to the Vehicle Objekt!!
 * @author Joel Zimmerli
 *
 */
public class vehicelsDataLyer<T,V> extends GridWorldFather<T> implements GridWorldVehicle<V>{
	List<V> vehicels;
	private T[][] values;
	public vehicelsDataLyer() {
		vehicels= new ArrayList<>();
	}
	
	

	@Override
	public void addVehicle(T vehicle, Point2D koordinates) {
		vehicels.add(vehicle);
		setValue(koordinates, vehicle);
		
	}

	@Override
	public List<V> getVehicles() {
		return vehicels;
	}
	
	@Override
	public String toString() {
		String out="";
		for (T[] row : values){
			for(T value : row){
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



	

}
