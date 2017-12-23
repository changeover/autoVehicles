package present;

import application.ApplicationContext;
import grid.GridWorldListener;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import vehicle.Creature;

public class frontPage extends DrawingPane{
	ApplicationContext applicationContext;
	public frontPage(ApplicationContext appli) {
		applicationContext=appli;
		g.setFill(Color.RED);
		g.fillOval(100, 100, 300, 300);
		appli.getVehicleGrid().addListener(new GridWorldListener() {
			
			@Override
			public void dataChanged() {
				repaint();
				
			}
		});
	}

	@Override
	protected void paint() {
		for (Creature vehicle :applicationContext.getVehicleGrid().getVehicles()){
			Point2D point = vehicle.getPosition();
			System.out.println("frontPage.paint()");
			g.setFill(Color.RED);
			g.fillOval(point.getX(), point.getY(), 100, 100);
	
		}
		
	}

}
