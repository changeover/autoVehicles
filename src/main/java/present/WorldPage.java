package present;

import application.ApplicationContext;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.paint.ImagePattern;
import logic.vehicle.Creature;

/**
 * This class extends an Canvas.
 * It's repaint this canvas with all Vehicles position 
 * and the Background from LightSources.
 * 
 * @author Joel Zimmerli, Kevin Streiter
 *
 */
public class WorldPage extends DrawingPane{
	private ApplicationContext applicationContext;
	public WorldPage(ApplicationContext appli) {
		applicationContext=appli;
		new AnimationTimer() {
			
			@Override
			public void handle(long currentNanoTime) {
                repaint();
			}
		}.start();

		this.setOnMouseMoved(event -> {
			int[] coordinates = new int[]{(int)event.getX(),(int)event.getY()};
			applicationContext.getLightGrid().updateSource(coordinates);

		});
	}

	@Override
	protected void painWorld() {
		g.drawImage(applicationContext.getLightGrid().getBackground(), 0, 0);
		for (Creature vehicle :applicationContext.getVehicleGrid().getVehicles()){
			Point2D point = vehicle.getPosition();
            ImagePattern imagePattern = new ImagePattern(applicationContext.getImageVehicle());
            g.setFill(imagePattern);
			g.fillOval(point.getX(), point.getY(), 20, 20);
		}
	}
}
