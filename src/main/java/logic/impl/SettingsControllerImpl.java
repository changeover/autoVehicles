package logic.impl;

import javafx.scene.control.Slider;
import logic.SettingsController;
import logic.SettingsControllerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Settings on the GUI
 * @author Andreas Ott, Gregor von Gunten
 *
 */
public class SettingsControllerImpl implements SettingsController {
    private List<SettingsControllerListener> listeners;
    private int vehicleCount;
    private int vehicleSpeed;
    private Slider vehicleCountSlider;
    private Slider vehicleSpeedSlider;
    public SettingsControllerImpl(){
        listeners = new ArrayList<>();
    }

    public int getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(int vehicleCount){
        this.vehicleCount = vehicleCount;

        fireDataChanged();
    }

    public int getVehicleSpeed() {
        return vehicleSpeed;
    }

    public void setVehicleSpeed(int vehicleSpeed) {
        this.vehicleSpeed = vehicleSpeed;

        fireDataChanged();
    }

    public void deactivateSlider(){
        this.vehicleSpeedSlider.setDisable(true);
        this.vehicleCountSlider.setDisable(true);
    }

    public void setSliders(Slider vehicleCountSlider, Slider vehicleSpeedSlider) {
        this.vehicleSpeedSlider = vehicleSpeedSlider;
        this.vehicleCountSlider = vehicleCountSlider;
    }

    public void activateSlider(){
        this.vehicleSpeedSlider.setDisable(false);
        this.vehicleCountSlider.setDisable(false);
    }

    public void addListener(SettingsControllerListener settingsControllerListener){
        listeners.add(settingsControllerListener);
    }
    private void fireDataChanged() {
        for (SettingsControllerListener listener : listeners) {
            listener.dataChanged();
        }
    }


}
