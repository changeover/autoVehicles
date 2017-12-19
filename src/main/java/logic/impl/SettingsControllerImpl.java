package logic.impl;

import javafx.scene.control.Slider;
import logic.SettingsController;
import logic.SettingsControllerListener;

import java.util.ArrayList;
import java.util.List;

public class SettingsControllerImpl implements SettingsController {
    private List<SettingsControllerListener> listeners;
    private int vehicleCount;
    private Slider slider;

    public SettingsControllerImpl(){
        listeners = new ArrayList<>();
    }
    public void setVehicleCount(int vehicleCount){
        this.vehicleCount = vehicleCount;
        fireDataChanged();
    }
    public int getVehicleCount(){
        return this.vehicleCount;
    }
    public void addListener(SettingsControllerListener settingsControllerListener){
        listeners.add(settingsControllerListener);
    }
    private void fireDataChanged() {
        for (SettingsControllerListener listener : listeners) {
            listener.dataChanged();
        }
    }

    public void deactivateSlider(){
        this.slider.setDisable(true);
    }
    public void setSlider(Slider slider){
        this.slider = slider;
    }
    public void activateSlider(){
        this.slider.setDisable(false);
    }



}
