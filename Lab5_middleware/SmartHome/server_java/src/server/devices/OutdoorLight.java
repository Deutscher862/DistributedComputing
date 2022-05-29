package devices;

import SmartHome.DeviceInfo;
import SmartHome.DeviceTurnedOffError;
import SmartHome.NightMode;
import com.zeroc.Ice.Current;
import main.ServerLogger;

import java.util.logging.Level;

public class OutdoorLight extends LightBulb implements SmartHome.OutdoorLight {
    private NightMode nightMode;

    public OutdoorLight(DeviceInfo info, NightMode nightMode) {
        super(info);
        this.nightMode = nightMode;
        this.nightMode.nightModeEnabled = false;
    }

    @Override
    public void setNightMode(NightMode nightMode, Current current) throws DeviceTurnedOffError {
        if (!lightbulbState.tunredOn) {
            ServerLogger.log(Level.WARNING, "Can not set nighMode: device is turned off");
            throw new DeviceTurnedOffError("LightBulb " + deviceInfo.name + " is turned off");
        }
        this.nightMode = nightMode;
        ServerLogger.log(Level.INFO, "Setting current nightMode value");
    }

    @Override
    public NightMode getNightMode(Current current) throws DeviceTurnedOffError {
        if (!lightbulbState.tunredOn) {
            ServerLogger.log(Level.WARNING, "Can not return nighMode: device is turned off");
            throw new DeviceTurnedOffError("LightBulb " + deviceInfo.name + " is turned off");
        }
        ServerLogger.log(Level.INFO, "Returning current nightMode value");
        return nightMode;
    }
}
