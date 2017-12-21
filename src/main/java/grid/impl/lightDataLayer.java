package grid.impl;

import java.util.HashMap;
import java.util.Map;

import grid.GridWorldSources;
import javafx.geometry.Point2D;

/**
 * Inside this class will be the logic of the light source
 * all others function are implemented by his father
 * @author Joel Zimmerli
 *
 */

public class lightDataLayer  extends GridWorldFather<Integer> implements GridWorldSources<Integer>{
	private Map<Point2D, Integer> sources;
	private int min;
	private int max;
	
	
	public lightDataLayer() {
		super();
		sources= new HashMap<>();
	}

	@Override
	public void addSource(int[] coordinates, Integer value) {
		sources.put(new Point2D(coordinates[0], coordinates[1]), value);
		
	}

	@Override
	public Map<Point2D, Integer> getSources() {
		return sources;
	}
	@Override
	public String toString() {
		return super.toString();
	}
	
	

	@Override
	public int getMinValue() {
		return min;
	}

	@Override
	public int getMaxValue() {
		return max;
	}
	public void findMinMax(){
		min = (int) super.values[0][0];
        max = (int) super.values[0][0];
        for (Integer[] row : super.values) {
            for (Integer value : row) {
                if (value > max) max = (int) value;
                if (value < min) min = (int) value;
            }
        }
        System.out.println("lightDataLayer.findMinMax()"+min+" : "+max);
	}

	@Override
	public void setData(Integer[][] values, String name) {{
			super.values=values;
			super.name = name;
			findMinMax();
			System.out.println("lightDataLayer.setData()");
			super.fireDataChanged();
		}
		
	}

}
