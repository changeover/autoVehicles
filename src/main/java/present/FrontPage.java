package present;

import application.ApplicationContext;
import grid.GridWorldListener;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import vehicle.Creature;

public class FrontPage extends DrawingPane{
	ApplicationContext applicationContext;
	public FrontPage(ApplicationContext appli) {
		applicationContext=appli;
		g.setFill(Color.RED);
		g.fillOval(100, 100, 300, 300);
		new AnimationTimer() {
			//siehe: https://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835
			@Override
			public void handle(long currentNanoTime) {
				 
				 repaint();
			}
		}.start();
	}

	@Override
	protected void paint() {
		System.out.println("frontPage.paint()");
		for (Creature vehicle :applicationContext.getVehicleGrid().getVehicles()){
			Point2D point = vehicle.getPosition();
			g.setFill(Color.RED);
			g.fillOval(point.getX(), point.getY(), 10, 10);
	
		}
		
	}


}
