package devices;

import SmartHome.DeviceInfo;
import SmartHome.Time;
import com.zeroc.Ice.Current;

public class RoomLight extends LightBulb implements SmartHome.RoomLight {
    private Time autoTurnOffTime;

    public RoomLight(DeviceInfo info, Time defaultTime) {
        super(info);
        autoTurnOffTime = defaultTime;
    }

    @Override
    public void setHours(int hours, Current current) {
        autoTurnOffTime.hours = hours;
    }

    @Override
    public void setMinutes(int minutes, Current current) {
        autoTurnOffTime.minutes = minutes;
    }

    @Override
    public Time getAutoTurnOffTime(Current current) {
        return autoTurnOffTime;
    }
}
