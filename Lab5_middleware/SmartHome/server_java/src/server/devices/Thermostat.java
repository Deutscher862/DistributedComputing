package devices;

import SmartHome.*;
import com.zeroc.Ice.Current;

public class
Thermostat implements SmartHome.Thermostat {
    private final DeviceInfo deviceInfo;
    private final ThermostatState state;

    public Thermostat(DeviceInfo deviceInfo, ThermostatState state) {
        this.deviceInfo = deviceInfo;
        this.state = state;
        this.state.turnedOn = true;
        this.state.temperature.value = 20.0f;
        this.state.airMisture.value = 0.45f;
    }

    @Override
    public DeviceInfo getDeviceInfo(Current current) {
        return deviceInfo;
    }

    @Override
    public void turnOn(Current current) {
        state.turnedOn = true;
    }

    @Override
    public void turnOff(Current current) {
        state.turnedOn = false;
    }

    @Override
    public Temperature getTemperature(Current current) throws DeviceTurnedOffError {
        if (!state.turnedOn) {
            throw new DeviceTurnedOffError("Thermostat " + deviceInfo.name + " is turned off");
        }
        return state.temperature;
    }

    @Override
    public void increaseTemperature(Current current) throws DeviceTurnedOffError {
        if (!state.turnedOn) {
            throw new DeviceTurnedOffError("Thermostat " + deviceInfo.name + " is turned off");
        }
        state.temperature.value += 0.2f;
    }

    @Override
    public void decreaseTemperature(Current current) throws DeviceTurnedOffError {
        if (!state.turnedOn) {
            throw new DeviceTurnedOffError("Thermostat " + deviceInfo.name + " is turned off");
        }
        state.temperature.value -= 0.2f;
    }

    @Override
    public AirMoisture getAirMoisture(Current current) throws DeviceTurnedOffError {
        if (!state.turnedOn) {
            throw new DeviceTurnedOffError("Thermostat " + deviceInfo.name + " is turned off");
        }
        return state.airMisture;
    }

    @Override
    public void increaseAirMoisture(Current current) throws DeviceTurnedOffError {
        if (!state.turnedOn) {
            throw new DeviceTurnedOffError("Thermostat " + deviceInfo.name + " is turned off");
        }
        state.temperature.value += 0.05f;
    }

    @Override
    public void decreaseAirMoisture(Current current) throws DeviceTurnedOffError {
        if (!state.turnedOn) {
            throw new DeviceTurnedOffError("Thermostat " + deviceInfo.name + " is turned off");
        }
        state.temperature.value -= 0.05f;
    }
}
