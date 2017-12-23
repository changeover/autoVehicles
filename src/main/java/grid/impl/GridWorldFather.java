package grid.impl;

import java.util.ArrayList;
import java.util.List;

import grid.GridWorld;
import grid.GridWorldListener;
import javafx.geometry.Point2D;

abstract class GridWorldFather<T> implements GridWorld<T> {
	protected T[][] values;
	protected String name;
	private List<GridWorldListener> listeners;

	
	public GridWorldFather() {
		name="";
		listeners= new ArrayList<>();
	}
	@Override
	abstract public void setData(T[][] values, String name);
	@Override
	public void setValue(Point2D coordinates, T value) {
		System.out.println("GridWorldFather.setValue()");
		values[(int) coordinates.getX()][(int) coordinates.getY()]=value;	
		fireDataChanged();
	}

	@Override
	public int getWidth() {
		if (values == null || values[0] == null) return 0;
		return values[0].length;
	}

	@Override
	public int getHeight() {
		if (values == null)return 0;
		return values.length;
	}
	
	@Override
	public T getValue(int[] koordinate) {
		return values[koordinate[0]][koordinate[1]];
	}

	@Override
	public String getName() {
		return name;
	}
	
	
	
	@Override
	public void addListener(GridWorldListener listener) {
		listeners.add(listener);
	}
	
	protected void fireDataChanged(){
		for (GridWorldListener listener: listeners){
			listener.dataChanged();
		}
	}
	@Override
	public String toString() {
		String out="";
		for (T[] row : values){
			for(T value : row){
				out=out+value+"\t|";
			}
			out+="\n";
		}
		return out;
	}

	
	
}
