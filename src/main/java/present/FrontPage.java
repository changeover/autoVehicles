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
		g.setFill(Color.RED);
		g.fillOval(100, 100, 100, 100);
		for (Creature vehicle :applicationContext.getVehicleGrid().getVehicles()){
			Point2D point = vehicle.getPosition();
			System.out.println("frontPage.paint()");
			g.setFill(Color.RED);
			g.fillOval(point.getX(), point.getY(), 10, 10);
	
		}
		
	}
	public void paintFront(){
		repaint();
	}
	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}

}
