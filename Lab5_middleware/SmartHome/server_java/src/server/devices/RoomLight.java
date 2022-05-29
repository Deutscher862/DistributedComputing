package devices;

import SmartHome.DeviceInfo;
import SmartHome.DeviceTurnedOffError;
import SmartHome.Time;
import com.zeroc.Ice.Current;
import main.ServerLogger;

import java.util.logging.Level;

public class RoomLight extends LightBulb implements SmartHome.RoomLight {
    private Time autoTurnOffTime;

    public RoomLight(DeviceInfo info, Time defaultTime) {
        super(info);
        autoTurnOffTime = defaultTime;
    }

    @Override
    public void setAutoTurnOffTime(Time time, Current current) throws DeviceTurnedOffError {
        if (!lightbulbState.tunredOn) {
            ServerLogger.log(Level.WARNING, "Can not set AutoTurnOffTime: device is turned off");
            throw new DeviceTurnedOffError("LightBulb " + deviceInfo.name + " is turned off");
        }
        autoTurnOffTime = time;
        ServerLogger.log(Level.INFO, "Setting current autoTurnOffTime");
    }

    @Override
    public Time getAutoTurnOffTime(Current current) throws DeviceTurnedOffError {
        if (!lightbulbState.tunredOn) {
            ServerLogger.log(Level.WARNING, "Can not return AutoTurnOffTime: device is turned off");
            throw new DeviceTurnedOffError("LightBulb " + deviceInfo.name + " is turned off");
        }
        ServerLogger.log(Level.INFO, "Returning current autoTurnOffTime");
        return autoTurnOffTime;
    }
}
