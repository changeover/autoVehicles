package grid.impl;

import java.util.HashMap;
import java.util.Map;

import grid.GridWorldSources;
import grid.PictureGenerator;
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

public class lightDataLayer  extends GridWorldFather<Integer> implements GridWorldSources<Integer>, PictureGenerator{
	private Map<Point2D, Integer> sources;
	private int min;
	private int max;
	private Image backGround;
	
	
	public lightDataLayer() {
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

	@Override
	public void makePictures() {
		System.out.println("BackgroundPic.makePictures()");
	    if ((getWidth() > 0) && (getHeight() > 0)) {
	    	System.out.println("make Pic");
	    	WritableImage writableImage = new WritableImage(getWidth(), getHeight());
	        int[] windowedValue = new int[1];
            double p = 0.7;
            for (int row = 0; row < getHeight(); row++) {
                for (int column = 0; column < getWidth(); column++) {
                    
                    int value = (values[row][column] * 255);
                    windowedValue[0] = (int) value/max;
                    //int value = lightData.getValue(new int[] {column, row});
                    //windowedValue[0] = (int) ((double)(value - lightData.getMinValue() /
                    //		(double)(lightData.getMaxValue() - lightData.getMinValue()) * 255));
                    //windowedValue[0] = Math.min(windowedValue[0], 255);
                    //windowedValue[0] = Math.max(windowedValue[0], 0);
                    writableImage.getPixelWriter().setColor(column, row, Color.rgb(windowedValue[0], windowedValue[0], 100));
                }
            }
            backGround = writableImage;
        }
	}

	@Override
	public Image getBackground() {
		System.out.println("lightDataLayer.getBackground()");
		return backGround;
	}
	
	public void calculateLight(Point2D position){
	    int lightValue = 999;
	    int scatterMultiplier = 5;
	    int difX;
	    int difY;
	    double difTotal;

	    for (int j = 0; j < values.length; j++) {
	        for (int i = 0; i < values[j].length; i++) {
	            difX = (int) (position.getX()- i);
	            difY = (int) (position.getY() - j);
	            difTotal = Math.sqrt(difX*difX + difY*difY);
	            values[j][i] += (int) (lightValue * Math.exp(-difTotal/scatterMultiplier));
	        }
	    }
	}

}
