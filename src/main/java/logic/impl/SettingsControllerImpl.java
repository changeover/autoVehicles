package logic.impl;

import logic.SettingsController;
import logic.SettingsControllerListener;

import java.util.ArrayList;
import java.util.List;

public class SettingsControllerImpl implements SettingsController {
    private List<SettingsControllerListener> listeners;
    private int vehicleCount;
    public SettingsControllerImpl(){
        listeners = new ArrayList<>();
    }
    public void setVehicleCount(int vehicleCount){
        this.vehicleCount = vehicleCount;
    }
    public int getVehicleCount(){
        return this.vehicleCount;
    }

}
