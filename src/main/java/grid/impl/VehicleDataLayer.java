package grid.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import grid.GridWorldVehicle;
import javafx.geometry.Point2D;
/**
 * This class is for saving all produced vehicles and to handle them 
 * with the calculation timing. 
 * Vehicles should always be added to the list, so it is going to be 
 * painted.
 * @author Joel Zimmerli
 *
 */
public class VehicleDataLayer<Vehicle> extends GridWorldFather<Vehicle> implements GridWorldVehicle<Vehicle, Vehicle>{
	List<Vehicle> vehicels;
	private ReentrantLock reentLock;
	private Condition waitForView, waitForBot;
	
	
	public VehicleDataLayer() {
		reentLock= new ReentrantLock();
		waitForView= reentLock.newCondition();
		waitForBot= reentLock.newCondition();
		vehicels= new ArrayList<>();
	}
	
	@Override
	public List<Vehicle> getVehicles() {
		reentLock.lock();
		try {
			System.out.println("VehicleDataLayer.getVehicles()");
			waitForView.signalAll();
			/**try{
				
				while(vehicels.isEmpty()){
					waitForBot.await();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}*/
			return vehicels;
		} finally {
			reentLock.unlock();
		}	
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
	}
	
	@Override
	public void setValue(Point2D coordinates, Vehicle value) {
		reentLock.lock();
		try {
			super.setValue(coordinates, value);
			waitForBot.signalAll();
			try {
				System.out.println("VehicleDataLayer.setValue()"+"wait for view");
				waitForView.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} finally {
			reentLock.unlock();
		}
		
	}

	@Override
	public void setData(Vehicle[][] values, String name) {
		super.values=values;
		super.name = name;
	}

	public void clearData(){
		vehicels.clear();
	}

}
