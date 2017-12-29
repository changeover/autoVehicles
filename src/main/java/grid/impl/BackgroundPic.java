package grid.impl;


import grid.GridWorldSources;
import grid.PictureGenerator;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class BackgroundPic implements PictureGenerator{
	
	private GridWorldSources<Double> lightData;
	private Image backGround;
	
	
	public BackgroundPic(GridWorldSources<Double> lightGrid) {
		this.lightData=lightGrid;
	}
	
	@Override
	public void makePictures() {
		
		    
	}

	
	public Image getBackground(){
		return backGround;
	}

}
