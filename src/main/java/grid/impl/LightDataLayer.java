package grid.impl;

import grid.GridWorldSources;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

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

	public LightDataLayer() {
		super();
		sources= new HashMap<>();
	}

	@Override
	public void addSource(int[] coordinates, Integer value) {
		Point2D point= new Point2D(coordinates[0], coordinates[1]);
		sources.put(point, value);
		calculateLight(point);
		findMinMax();
		makePictures();
		fireDataChanged();
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
		min = super.values[0][0];
        max = super.values[0][0];
        for (Double[] row : super.values) {
            for (Double value : row) {
                if (value > max) max = value;
                if (value < min) min = value;
            }
        }
        //System.out.println("lightDataLayer.findMinMax()"+min+" : "+max);
	}

	@Override
	public void setData(Double[][] values, String name) {{
			super.values=values;
			super.name = name;
			findMinMax();
			//System.out.println("lightDataLayer.setData()");
			super.fireDataChanged();
		}

	}

	private void makePictures() {
		//System.out.println("BackgroundPic.makePictures()");
	    if ((getWidth() > 0) && (getHeight() > 0)) {
	    	//System.out.println("make Pic");
	    	WritableImage writableImage = new WritableImage(getWidth(), getHeight());
			int windowedValue;
			double p = 0.7;
            for (int row = 0; row < getWidth(); row++) {
                for (int column = 0; column < getHeight(); column++) {

                    Double value = (values[row][column] * 255);
					windowedValue = 255 - (int) (value / max);

					writableImage.getPixelWriter().setColor(row, column, Color.rgb(windowedValue, windowedValue, 100));
				}
            }
            backGround = writableImage;
        }
	}

	public Image getBackground() {
		//System.out.println("lightDataLayer.getBackground()");
		return backGround;
	}

	public void calculateLight(Point2D position){
	    int lightValue = 1;
	    int scatterMultiplier =5;
	    int difX;
	    int difY;
	    double difTotal;
	   /*
		for (int j = 0; j < values.length; j++) {
	        for (int i = 0; i < values[j].length; i++) {
	            difX = (int) (position.getX()- i);
	            difY = (int) (position.getY() - j);
	            difTotal = Math.sqrt(difX*difX + difY*difY);
	            values[j][i] += (lightValue * Math.exp(-difTotal/scatterMultiplier));
	        }
	    }*/
	    double p = 0.1;
        for (int x = 0; x < values.length; x++) {
            for (int y = 0; y < values[x].length; y++) {
                int xPos =x-(int) position.getX(); //x - values[x].length / 2 + 1;
                int yPos =y-(int) position.getY(); //y - values.length / 2 + 1;
                values[x][y] += 1 / (2 * 6.1 * Math.sqrt(1 - p)) *
                        Math.exp(-1 / (2 * (1 - Math.sqrt(1 - Math.pow(p, 2))) *
                                (xPos * xPos + 2 * p * xPos * yPos + yPos * yPos)));

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

	public enum reachedBorder {TOP, BOTTOM, RIGHT, LEFT, NONE}
}
