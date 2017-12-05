package grid.settings;

import application.ApplicationContext;
import javafx.scene.layout.StackPane;
import logic.SettingsController;

public class Settings extends StackPane {
    SettingsController settingsController;
    public Settings(ApplicationContext applicationContext){
        settingsController = applicationContext.getSettingsController();
    }

}
