package present;

import grid.settings.Settings;
import application.ApplicationContext;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainPanel extends BorderPane{
	private FrontPage front;
    public MainPanel(final ApplicationContext applicationContext){
        final int SETTINGHEIGHT = 100;
        StackPane visualPane = new StackPane();
        StackPane settingPane = new StackPane(new Settings(applicationContext));
        TilePane buttonPane = new TilePane();
        buttonPane.setPadding(new Insets(5,10,5,10));
        buttonPane.setHgap(30);
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.setDividerPositions(1,SETTINGHEIGHT/applicationContext.getWindowHeight());
        splitPane.setDividerPositions(0,(double)(applicationContext.getWindowHeight() - SETTINGHEIGHT) / (double)applicationContext.getWindowHeight());
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        Button exitButton = new Button("Exit");

        front = new FrontPage(applicationContext);

        buttonPane.setMaxHeight(30);
        settingPane.setMaxHeight(30);
        visualPane.setMinHeight(applicationContext.getWindowHeight());
        splitPane.setMaxWidth(applicationContext.getWindowWidth());
        splitPane.getItems().addAll(buttonPane,visualPane,settingPane);


        buttonPane.getChildren().addAll(startButton,stopButton,exitButton);
        startButton.setOnAction(event -> applicationContext.run());
        stopButton.setOnAction(event -> applicationContext.stop());
        exitButton.setOnAction(event -> System.exit(0));

        visualPane.getChildren().add(front);



        //MenuBar menuBar = new MenuBar();
        //Menu menu;
        //MenuItem menuItem;
//
        //menu = new Menu("Application");
        //menuBar.getMenus().add(menu);
//
        //menuItem = new MenuItem("Start");
        //menu.getItems().add(menuItem);
        //menuItem.setOnAction(event -> applicationContext.run());
//
        //menuItem = new MenuItem("Stop");
        //menu.getItems().add(menuItem);
        //menuItem.setOnAction(event -> applicationContext.stop());
//
        //menuItem = new MenuItem("Exit");
        //menu.getItems().add(menuItem);
        //menuItem.setOnAction(event -> System.exit(0));
        //
//
//
        //setTop(menuBar);
        setCenter(splitPane);

    }
}

