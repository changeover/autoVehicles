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

public class lightDataLayer<T>  extends GridWorldFather<T> implements GridWorldSources<T>{
private Map<Point2D, Integer> sources;
private Integer[][] values;
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
			// TODO Auto-generated method stub
			return super.toString();
		}
	
	public void findMinMax(){
		min = values[0][0];
        max = values[0][0];

        for (Integer[] row : values) {
            for (int value : row) {
                if (value > max) max = value;
                if (value < min) min = value;
            }
        }
	}

	@Override
	public int getMinValue() {
		return min;
	}

	@Override
	public int getMaxValue() {
		return max;
	}

}
