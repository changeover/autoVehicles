package present;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

/**
 * This class provides the basic infrastructure for drawing into a canvas.
 * Clients should extend this class and then simply implement the paint() method.
 * Inside paint() the graphics context g is available for issuing drawing commands.
 * The canvas is automatically created and updated if the size of the pane changes.
 */
public abstract class DrawingPane extends Pane {
    private Canvas canvas;
    protected GraphicsContext g;

    public DrawingPane() {
        canvas = new Canvas();
        getChildren().add(canvas);

        g = canvas.getGraphicsContext2D();
  
    }

    /**
     * This method gets called by JavaFX every time the size of the window changes.
     * If you need to adapt e.g. any coordinate systems when this happens then override this method,
     * but don't forget to call "super" at the end.
     */
    @Override
    protected void layoutChildren() {
        super.layoutChildren();

        double width = getWidth();
        double height = getHeight();

        if ((width != canvas.getWidth()) || (height != canvas.getHeight())) {
            canvas.setWidth(width);
            canvas.setHeight(height);
            repaint();
        }
    }

    /**
     * Call this method from inside your code when the component needs to be updated.
     */
    public void repaint() {
        g.clearRect(0, 0, getWidth(), getHeight());
        paitWorld();
    }

    /**
     * This method must be overridden with the actual drawing code.
     */
    protected abstract void paitWorld();

}
