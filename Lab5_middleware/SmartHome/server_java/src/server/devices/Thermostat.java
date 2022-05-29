package devices;

import SmartHome.*;
import com.zeroc.Ice.Current;
import main.ServerLogger;

import java.util.logging.Level;

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
        ServerLogger.log(Level.INFO, "Returning device info");
        return deviceInfo;
    }

    @Override
    public void turnOn(Current current) {
        ServerLogger.log(Level.INFO, "Turning thermostat on");
        state.turnedOn = true;
    }

    @Override
    public void turnOff(Current current) {
        ServerLogger.log(Level.INFO, "Turning thermostat off");
        state.turnedOn = false;
    }

    @Override
    public ThermostatState getState(Current current) throws DeviceTurnedOffError {
        if (!state.turnedOn) {
            ServerLogger.log(Level.WARNING, "Can not return state: device is turned off");
            throw new DeviceTurnedOffError("Thermostat " + deviceInfo.name + " is turned off");
        }
        ServerLogger.log(Level.INFO, "Returning thermostat state");
        return state;
    }

    @Override
    public Temperature getTemperature(Current current) throws DeviceTurnedOffError {
        if (!state.turnedOn) {
            ServerLogger.log(Level.WARNING, "Can not return temperature: device is turned off");
            throw new DeviceTurnedOffError("Thermostat " + deviceInfo.name + " is turned off");
        }
        ServerLogger.log(Level.INFO, "Returning thermostat temperature");
        return state.temperature;
    }

    @Override
    public void increaseTemperature(Current current) throws DeviceTurnedOffError, ValueLimitReachedError {
        if (!state.turnedOn) {
            ServerLogger.log(Level.WARNING, "Can not increase temperature: device is turned off");
            throw new DeviceTurnedOffError("Thermostat " + deviceInfo.name + " is turned off");
        }
        if (state.temperature.value >= 27.0f) {
            ServerLogger.log(Level.WARNING, "Can not increase temperature: limit reached");
            throw new ValueLimitReachedError("Temperature limit reached, current value: " + state.temperature.value);
        }
        state.temperature.value += 0.5f;
        ServerLogger.log(Level.INFO, "Increasing thermostat temperature");
    }

    @Override
    public void decreaseTemperature(Current current) throws DeviceTurnedOffError, ValueLimitReachedError {
        if (!state.turnedOn) {
            ServerLogger.log(Level.WARNING, "Can not decrease temperature: device is turned off");
            throw new DeviceTurnedOffError("Thermostat " + deviceInfo.name + " is turned off");
        }
        if (state.temperature.value <= 15.0f) {
            ServerLogger.log(Level.WARNING, "Can not decrease temperature: limit reached");
            throw new ValueLimitReachedError("Temperature limit reached, current value: " + state.temperature.value);
        }
        state.temperature.value -= 0.5f;
        ServerLogger.log(Level.INFO, "Decreasing thermostat temperature");
    }

    @Override
    public AirMoisture getAirMoisture(Current current) throws DeviceTurnedOffError {
        if (!state.turnedOn) {
            ServerLogger.log(Level.WARNING, "Can not return airMoisture: device is turned off");
            throw new DeviceTurnedOffError("Thermostat " + deviceInfo.name + " is turned off");
        }
        ServerLogger.log(Level.INFO, "Returning thermostat airMoisture");
        return state.airMisture;
    }

    @Override
    public void increaseAirMoisture(Current current) throws DeviceTurnedOffError, ValueLimitReachedError {
        if (!state.turnedOn) {
            ServerLogger.log(Level.WARNING, "Can not increase airMoisture: device is turned off");
            throw new DeviceTurnedOffError("Thermostat " + deviceInfo.name + " is turned off");
        }
        if (state.airMisture.value >= 0.6f) {
            ServerLogger.log(Level.WARNING, "Can not increase airMoisture: limit reached");
            throw new ValueLimitReachedError("AirMoisture limit reached, current value: " + state.airMisture.value);
        }
        state.airMisture.value += 0.05f;
        ServerLogger.log(Level.INFO, "Increasing thermostat airMoisture");
    }

    @Override
    public void decreaseAirMoisture(Current current) throws DeviceTurnedOffError, ValueLimitReachedError {
        if (!state.turnedOn) {
            ServerLogger.log(Level.WARNING, "Can not decrease airMoisture: device is turned off");
            throw new DeviceTurnedOffError("Thermostat " + deviceInfo.name + " is turned off");
        }
        if (state.airMisture.value <= 0.4f) {
            ServerLogger.log(Level.WARNING, "Can not decrease airMoisture: limit reached");
            throw new ValueLimitReachedError("AirMoisture limit reached, current value: " + state.airMisture.value);
        }
        state.airMisture.value -= 0.05f;
        ServerLogger.log(Level.INFO, "Decreasing thermostat airMoisture");
    }
}
