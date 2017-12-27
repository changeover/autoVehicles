package application;


import javafx.scene.image.ImageView;
import logic.*;
import logic.impl.*;
import world.LightMap;
import world.WorldPane;

public class ApplicationContext {
    private SettingsController settingsController;
    private LightMap lightMap;
    private WorldPane worldPane;

    public ApplicationContext(int width, int height) {
        settingsController = new SettingsControllerImpl();
        this.lightMap = new LightMap(width, height);
        this.worldPane = new WorldPane();
        this.worldPane.getChildren().add(new ImageView(getLightMap().getBackGround()));

    }

    public void run(){

    }
    public void stop(){

    }
    public SettingsController getSettingsController(){
        return this.settingsController;
    }

    public LightMap getLightMap() {
        return lightMap;
    }

    public WorldPane getWorldPane() {
        return worldPane;
    }
}
