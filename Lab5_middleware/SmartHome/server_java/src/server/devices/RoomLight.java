package devices;

import SmartHome.DeviceInfo;
import SmartHome.DeviceTurnedOffError;
import SmartHome.Time;
import com.zeroc.Ice.Current;

public class RoomLight extends LightBulb implements SmartHome.RoomLight {
    private Time autoTurnOffTime;

    public RoomLight(DeviceInfo info, Time defaultTime) {
        super(info);
        autoTurnOffTime = defaultTime;
    }

    @Override
    public void setAutoTurnOffTime(Time time, Current current) throws DeviceTurnedOffError {
        if (!lightbulbState.tunredOn) {
            throw new DeviceTurnedOffError("LightBulb " + deviceInfo.name + " is turned off");
        }
        autoTurnOffTime = time;
    }

    @Override
    public Time getAutoTurnOffTime(Current current) throws DeviceTurnedOffError {
        if (!lightbulbState.tunredOn) {
            throw new DeviceTurnedOffError("LightBulb " + deviceInfo.name + " is turned off");
        }
        return autoTurnOffTime;
    }
}
