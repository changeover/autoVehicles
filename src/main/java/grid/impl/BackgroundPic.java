package grid.impl;


import grid.GridWorldSources;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import present.PictureGenerator;

public class BackgroundPic implements PictureGenerator{
	
	private GridWorldSources<Integer> lightData;
	private Image backGround;
	
	
	public BackgroundPic(GridWorldSources<Integer> lightGrid) {
		this.lightData=lightGrid;
	}
	
	@Override
	public void makePictures() {
		System.out.println("BackgroundPic.makePictures()");
	    if ((lightData.getWidth() > 0) && (lightData.getHeight() > 0)) {
	    	System.out.println("make Pic");
	    	WritableImage writableImage = new WritableImage(lightData.getWidth(), lightData.getHeight());
	        int[] windowedValue = new int[1];
            for (int row = 0; row < lightData.getHeight(); row++) {
                for (int column = 0; column < lightData.getWidth(); column++) {
                    int value = lightData.getValue(new int[] {column, row});
	                    windowedValue[0] = (int) ((double)(value - lightData.getMinValue() / 
	                    		(double)(lightData.getMaxValue() - lightData.getMinValue()) * 255));
	                    windowedValue[0] = Math.min(windowedValue[0], 255);
	                    windowedValue[0] = Math.max(windowedValue[0], 0);
	                    writableImage.getPixelWriter().setColor(column, row,Color.grayRgb(windowedValue[0]));
                }
            }
            backGround = writableImage;
        }
		    
	}

	

	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		
	}
	
	public Image getBackground(){
		return backGround;
	}

}
