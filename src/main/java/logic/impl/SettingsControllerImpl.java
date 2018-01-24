package logic.impl;

import javafx.scene.control.Slider;
import logic.SettingsController;
import logic.SettingsControllerListener;

import java.util.ArrayList;
import java.util.List;

public class SettingsControllerImpl implements SettingsController {
    private List<SettingsControllerListener> listeners;
    private int vehicleCount;
    private int vehicleSpeed;
    private Slider countSlider;
    private Slider speedSlider;
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
        this.speedSlider.setDisable(true);
        this.countSlider.setDisable(true);
    }

    public void setSliders(Slider countSlider, Slider speedSlider) {
        this.speedSlider = speedSlider;
        this.countSlider = countSlider;
    }

    public void activateSlider(){
        this.speedSlider.setDisable(false);
        this.countSlider.setDisable(false);
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
