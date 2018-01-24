package grid.impl;

import java.util.HashMap;
import java.util.Map;

import grid.GridWorldSources;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Inside this class will be the logic of the light source
 * all others function are implemented by his father
 * @author Joel Zimmerli
 *
 */

public class LightDataLayer  extends GridWorldFather<Double> implements GridWorldSources<Double>{
	private Map<Point2D, Integer> sources;
	private Double min;
	private Double max;
	private Image backGround;
	private Double[][] lightLayer;

	public enum reachedBorder {TOP,BOTTOM,RIGHT,LEFT,NONE};

	
	public LightDataLayer() {
		super();
		
		sources= new HashMap<>();
	}

	@Override
	public void addSource(int[] coordinates, Integer value) {
		Point2D point= new Point2D(coordinates[0], coordinates[1]);
		sources.put(point, value);
		changeWindow(point);
		makePictures();
	}
	private void changeWindow(Point2D position){
		//Calculate difference of Source to center
		int xDiffCenter=(int) (-position.getX()+values.length/2);
		int yDiffCenter=(int) (-position.getY()+values[0].length/2);
		int xValuesStart=(int) lightLayer.length/2-values.length/2;
		int yValuesStart=(int) lightLayer[0].length/2-values[0].length/2;
		for (int i = 0; i<values.length;i++){
			for (int j = 0; j<values[0].length;j++){
				values[i][j]=lightLayer[i+xValuesStart+xDiffCenter][j+yValuesStart+yDiffCenter];
			}
		}
	}

	@Override
	public void deleteSources(){
		resetValues();
		sources.clear();
		makePictures();
		fireDataChanged();
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
	public Double getMinValue() {
		return min;
	}

	@Override
	public Double getMaxValue() {
		return max;
	}
	private void findMinMax(){
		min = lightLayer[0][0];
        max = lightLayer[0][0];
        for (Double[] row : lightLayer) {
            for (Double value : row) {
                if (value > max) max = value;
                if (value < min) min = value;
            }
        }
	}

	@Override
	public void setData(Double[][] values, String name) {{
			super.values=values;
			super.name = name;
			lightLayer= new Double[values.length*4][values[0].length*4];
			calculateLightLayer();
			findMinMax();
			super.fireDataChanged();
		}
		
	}

	private void makePictures() {
		
	    	WritableImage writableImage = new WritableImage(getWidth(), getHeight());
	        int[] windowedValue = new int[1];
            for (int row = 0; row < getWidth(); row++) {
                for (int column = 0; column < getHeight(); column++) {
                    Double value = (values[row][column] * 255);
                    windowedValue[0] = (int) (value/max);
                    
                    writableImage.getPixelWriter().setColor(row, column, Color.rgb(windowedValue[0], windowedValue[0], 100));
                }
            }
            backGround = writableImage;
        
	}

	
	public Image getBackground() {
		return backGround;
	}
	
	public void calculateLightLayer(){
		Point2D position= new Point2D(lightLayer.length/2, lightLayer[0].length/2);
	    double p = 0.1;
        for (int x = 0; x < lightLayer.length; x++) {
            for (int y = 0; y < lightLayer[x].length; y++) {
                int xDiff =x-(int) position.getX(); 
                int yDiff =y-(int) position.getY(); 
                lightLayer[x][y] = 1 / (2 * 6.1 * Math.sqrt(1 - p)) *
                        Math.exp(-1 / (2 * (1 - Math.sqrt(1 - Math.pow(p, 2))) *
                                (xDiff * xDiff + 2 * p * xDiff * yDiff + yDiff * yDiff)));

            }
        }
	}

	public reachedBorder checkForCollision(int x ,int y){
		if(x <= 0){
			return reachedBorder.LEFT;
		}
		else if(x >= values.length-1) {
			return reachedBorder.RIGHT;
		}
		else if(y <= 0) {
			return reachedBorder.TOP;
		}
		else if(y >= values[0].length-1) {
			return reachedBorder.BOTTOM;
		}
		else {
			return reachedBorder.NONE;
		}
	}
}
