package logic;

public interface SettingsController {
    void setVehicleCount(int vehicleCount);

    int getVehicleCount();

    void addListener(SettingsControllerListener settingsControllerListener);
}
