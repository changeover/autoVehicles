package present;

import application.ApplicationContext;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.paint.ImagePattern;
import vehicle.Creature;

public class FrontPage extends DrawingPane{
	private ApplicationContext applicationContext;
	public FrontPage(ApplicationContext appli) {
		applicationContext=appli;
		new AnimationTimer() {
			//siehe: https://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835
			@Override
			public void handle(long currentNanoTime) {
                repaint();
			}
		}.start();

		this.setOnMouseMoved(event -> {

			applicationContext.getLightGrid().deleteSources();
			int[] coord = new int[]{(int)event.getX(),(int)event.getY()};
			applicationContext.getLightGrid().addSource(coord, 100);

		});
	}

	@Override
	protected void paint() {
		//System.out.println("frontPage.paint()");
		g.drawImage(applicationContext.getLightGrid().getBackground(), 0, 0);
		for (Creature vehicle :applicationContext.getVehicleGrid().getVehicles()){
			Point2D point = vehicle.getPosition();
            ImagePattern imagePattern = new ImagePattern(applicationContext.getImage());
            g.setFill(imagePattern);
			g.fillOval(point.getX(), point.getY(), 20, 20);
		}
	}
}
