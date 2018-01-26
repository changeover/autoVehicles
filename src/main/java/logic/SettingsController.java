package logic;

import javafx.scene.control.Slider;
/**
 * HAs the main Functionality of the Setting
 * @author Andreas Ott, Gregor von Gunten
 *
 */

public interface SettingsController {
    int getVehicleSpeed();

    void setVehicleSpeed(int vehicleCount);

    int getVehicleCount();

    void setVehicleCount(int vehicleCount);

    void setSliders(Slider countSlider, Slider speedSlider);

    void deactivateSlider();

    void activateSlider();

    void addListener(SettingsControllerListener settingsControllerListener);
}
