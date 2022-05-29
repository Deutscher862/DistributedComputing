package devices;

import SmartHome.*;
import com.zeroc.Ice.Current;
import main.ServerLogger;

import java.util.logging.Level;

public class LightBulb implements SmartHome.LightBulb {
    protected final DeviceInfo deviceInfo;
    protected final LightBulbState lightbulbState;

    public LightBulb(DeviceInfo info) {
        this.deviceInfo = info;
        this.lightbulbState = new LightBulbState();
    }

    @Override
    public DeviceInfo getDeviceInfo(Current current) {
        ServerLogger.log(Level.INFO, "Returning device info");
        return deviceInfo;
    }

    @Override
    public LightBulbState getLightBulbState(Current current) {
        ServerLogger.log(Level.INFO, "Returning lightbulb state");
        return lightbulbState;
    }

    @Override
    public Color getColor(Current current) throws DeviceTurnedOffError {
        if (!lightbulbState.tunredOn) {
            ServerLogger.log(Level.WARNING, "Can not return color: device is turned off");
            throw new DeviceTurnedOffError("LightBulb " + deviceInfo.name + " is turned off");
        }
        ServerLogger.log(Level.INFO, "Returning current color");
        return lightbulbState.color;
    }

    @Override
    public void setColor(String newColor, Current current) throws DeviceTurnedOffError, InvalidColorError {
        if (!lightbulbState.tunredOn) {
            ServerLogger.log(Level.WARNING, "Can not set color: device is turned off");
            throw new DeviceTurnedOffError("LightBulb " + deviceInfo.name + " is turned off");
        }
        try {
            lightbulbState.color = Color.valueOf(newColor.toUpperCase());
            ServerLogger.log(Level.INFO, "Setting new color");
        } catch (IllegalArgumentException e) {
            ServerLogger.log(Level.WARNING, "Can not set color: color name is invalid");
            throw new InvalidColorError("Color " + newColor + " is not a valid color name");
        }
    }

    @Override
    public void turnOn(Current current) {
        ServerLogger.log(Level.INFO, "Turning lightbulb on");
        lightbulbState.tunredOn = true;
    }

    @Override
    public void turnOff(Current current) {
        ServerLogger.log(Level.INFO, "Turning lightbulb off");
        lightbulbState.tunredOn = false;
    }
}
