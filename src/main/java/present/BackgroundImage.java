package present;

import application.ApplicationContext;
import grid.GridWorldListener;
import javafx.scene.image.ImageView;

public class BackgroundImage extends ImageView{
	ApplicationContext applicationContext;
	
	public BackgroundImage(ApplicationContext appli) {
		super();
		applicationContext=appli;		
		applicationContext.getLightGrid().addListener(new GridWorldListener(){

			@Override
			public void dataChanged() {
				System.out.println("BackgroundImgae.BackgroundImgae(...).new GridWorldListener() {...}.dataChanged()");
				appli.getBackgroudnPic().makePictures();
				setImage(appli.getBackgroudnPic().getBackground());
			}
			
		});
		
		
		
	}

}
