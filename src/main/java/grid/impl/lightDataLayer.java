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
private Map<Point2D, Double> sources;
	
public lightDataLayer() {
	super();
	sources= new HashMap<>();
	
}

@Override
	public void addSource(int[] coordinates, double value) {
		sources.put(new Point2D(coordinates[0], coordinates[1]), value);
		
	}

	@Override
	public Map<Point2D, Double> getSources() {
		return sources;
	}
	@Override
		public String toString() {
			// TODO Auto-generated method stub
			return super.toString();
		}
	

}
