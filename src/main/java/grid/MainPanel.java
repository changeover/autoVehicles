package grid;

import grid.settings.Settings;
import application.ApplicationContext;
import javafx.geometry.Orientation;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import world.WorldPane;

public class MainPanel extends BorderPane{

    private WorldPane worldPane;

    public MainPanel(final ApplicationContext applicationContext, int windowWidth, int windowHeight){
        final int SETTINGHEIGHT = 100;
        this.worldPane = applicationContext.getWorldPane();
        StackPane settingPane = new StackPane(new Settings(applicationContext));
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.setDividerPosition(0,(double)(windowHeight - SETTINGHEIGHT) / (double)windowHeight);
        splitPane.getItems().addAll(worldPane, settingPane);
        MenuBar menuBar = new MenuBar();
        Menu menu;
        MenuItem menuItem;

        menu = new Menu("Application");
        menuBar.getMenus().add(menu);

        menuItem = new MenuItem("Start");
        menu.getItems().add(menuItem);
        menuItem.setOnAction(event -> applicationContext.run());

        menuItem = new MenuItem("Stop");
        menu.getItems().add(menuItem);
        menuItem.setOnAction(event -> applicationContext.stop());

        menuItem = new MenuItem("Exit");
        menu.getItems().add(menuItem);
        menuItem.setOnAction(event -> System.exit(0));

        setTop(menuBar);
        setCenter(splitPane);

    }

    public Pane getWorldPane() {
        return worldPane;
    }
}
