package logic;

import javafx.scene.control.Slider;

public interface SettingsController {
    void setVehicleCount(int vehicleCount);

    int getVehicleCount();

    void setSlider(Slider slider);

    void deactivateSlider();

    void activateSlider();

    void addListener(SettingsControllerListener settingsControllerListener);
}
