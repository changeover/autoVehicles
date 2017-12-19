package logic;

import javafx.scene.control.Slider;

public interface SettingsController {
    void setVehicleCount(int vehicleCount);

    int getVehicleCount();

    void addListener(SettingsControllerListener settingsControllerListener);

    void deactivateSlider();

    void setSlider(Slider slider);

    void activateSlider();

}
