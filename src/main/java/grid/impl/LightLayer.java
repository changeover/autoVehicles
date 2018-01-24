package grid.impl;

import grid.GridWorldSources;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Inside this class will be the logic of the light source,
 * As well its create the picture for the background
 * all others function are implemented by his father
 * @author Joel Zimmerli, Cesar De Carmo, Kevin Streiter, Gregor von Gunten
 *
 */

public class LightLayer  extends GridWorldFather<Double> implements GridWorldSources<Double>{
	private List<LightSource> lightSources;
	private Double minValueLight;
	private Double maxValueLight;
	private Image backGround;
	private Double[][] lightLayer;

	public LightLayer() {
		super();
		lightSources= new ArrayList<>();
	}

	@Override
	public void addSource(int[] coordinates) {
		lightSources.add(new LightSource(coordinates, 0));
		changeIllumination(coordinates);
		makePictures();
	}
	@Override
	public void updateSource(int[] coordinates) {
		lightSources.get(0).setKoordinates(coordinates);
		changeIllumination(coordinates);
		makePictures();
	}
	private void changeIllumination(int[] coordinates){
		//Calculate difference of Source to center
		int xDiffCenter=(int) (-coordinates[0]+values.length/2);
		int yDiffCenter=(int) (-coordinates[1]+values[0].length/2);
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
		lightSources.clear();
		changeIllumination(new int[]{1,1});
		makePictures();
	}

	

	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public Double getMinValue() {
		return minValueLight;
	}

	@Override
	public Double getMaxValue() {
		return maxValueLight;
	}

	private void findMinMax(){
		minValueLight = lightLayer[0][0];
        maxValueLight = lightLayer[0][0];
        for (Double[] row : lightLayer) {
            for (Double value : row) {
                if (value > maxValueLight) maxValueLight = value;
                if (value < minValueLight) minValueLight = value;
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
			int windowedValue;
            for (int row = 0; row < getWidth(); row++) {
                for (int column = 0; column < getHeight(); column++) {
                    Double value = (values[row][column] * 255);
					windowedValue = 255 - (int) (value / maxValueLight);

					writableImage.getPixelWriter().setColor(row, column, Color.rgb(windowedValue, windowedValue, 100));
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

	public enum reachedBorder {TOP, BOTTOM, RIGHT, LEFT, NONE}
}
