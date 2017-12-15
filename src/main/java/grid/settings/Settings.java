package grid.settings;

import application.ApplicationContext;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.SettingsController;

public class Settings extends StackPane {
    SettingsController settingsController;
    public Settings(ApplicationContext applicationContext){
        settingsController = applicationContext.getSettingsController();
        final HBox controlPanel = new HBox();
        controlPanel.setPadding(new Insets(0, 0, 10, 0));
        controlPanel.setAlignment(Pos.CENTER);
        final Label vehicleCountLabel = new Label("Vehicle Count: ");
        Slider vehicleCount = new Slider();
        Label count = new Label();
        vehicleCount.setMin(0);
        vehicleCount.setMax(1000);
        vehicleCount.setValue(100);
        settingsController.setVehicleCount(100);
        vehicleCount.valueProperty().addListener((observable, oldValue, newValue) -> settingsController.setVehicleCount(newValue.intValue()));
        settingsController.addListener(() -> {count.setText(String.valueOf(settingsController.getVehicleCount()));});
        controlPanel.getChildren().add(vehicleCountLabel);
        controlPanel.getChildren().add(vehicleCount);
        controlPanel.getChildren().add(count);

        VBox vBox = new VBox();
        vBox.getChildren().add(controlPanel);
        vBox.setPadding(new Insets(10));
        getChildren().add(vBox);
    }

}
