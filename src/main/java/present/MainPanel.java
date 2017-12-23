package present;

import grid.settings.Settings;
import application.ApplicationContext;
import javafx.geometry.Orientation;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MainPanel extends BorderPane{
    public MainPanel(final ApplicationContext applicationContext, int windowWidth, int windowHeight){
        final int SETTINGHEIGHT = 100;
        StackPane topPane = new StackPane();
        StackPane settingPane = new StackPane(new Settings(applicationContext));
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.setDividerPosition(0,(double)(windowHeight - SETTINGHEIGHT) / (double)windowHeight);
        
        BackgroundImage imageView = new BackgroundImage(applicationContext);
        imageView.fitHeightProperty().bind(topPane.heightProperty());
        imageView.fitWidthProperty().bind(topPane.widthProperty());
        
        frontPage front = new frontPage(applicationContext);
        
        
        
        
        splitPane.getItems().addAll(topPane,settingPane);
        topPane.getChildren().add(imageView);
        topPane.getChildren().add(front);
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
}
