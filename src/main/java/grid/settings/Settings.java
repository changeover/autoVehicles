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
        final Label vehicleCountLabel = new Label("Number of vehicles: ");
        final Label vehicleSpeedLabel = new Label("Speed: ");
        Slider vehicleCount = new Slider();
        Slider vehicleSpeed = new Slider();
        Label count = new Label();
        Label speed = new Label();
        vehicleCount.setMin(1);
        vehicleCount.setMax(1000);
        vehicleCount.setValue(100);
        vehicleSpeed.setMin(1);
        vehicleSpeed.setMax(100);
        vehicleSpeed.setValue(50);

        settingsController.setVehicleCount(100);
        settingsController.setVehicleSpeed(50);
        settingsController.setSliders(vehicleCount, vehicleSpeed);
        vehicleCount.valueProperty().addListener((observable, oldValue, newValue) -> settingsController.setVehicleCount(newValue.intValue()));
        vehicleSpeed.valueProperty().addListener((observable, oldValue, newValue) -> settingsController.setVehicleSpeed(newValue.intValue()));
        count.setText(String.valueOf(settingsController.getVehicleCount()));
        speed.setText(String.valueOf(settingsController.getVehicleSpeed()));
        settingsController.addListener(() -> {count.setText(String.valueOf(settingsController.getVehicleCount()));});
        settingsController.addListener(() -> {
            speed.setText(String.valueOf(settingsController.getVehicleSpeed()));
        });
        controlPanel.getChildren().add(vehicleCountLabel);
        controlPanel.getChildren().add(vehicleCount);
        controlPanel.getChildren().add(count);
        controlPanel.getChildren().add(vehicleSpeedLabel);
        controlPanel.getChildren().add(vehicleSpeed);
        controlPanel.getChildren().add(speed);

        VBox vBox = new VBox();
        vBox.getChildren().add(controlPanel);
        vBox.setPadding(new Insets(10));
        getChildren().add(vBox);
    }

}
