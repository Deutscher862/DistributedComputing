package server.devices;

import SmartHome.DeviceInfo;
import SmartHome.Time;
import com.zeroc.Ice.Current;

class RoomLight extends LightBulb implements SmartHome.RoomLight {
    private Time autoTurnOffTime;

    RoomLight(DeviceInfo info, Time defaultTime) {
        super(info);
        autoTurnOffTime = defaultTime;
    }

    @Override
    public void setAutoTurnOffTime(Time time, Current current) {
        autoTurnOffTime = time;
    }

    @Override
    public Time getAutoTurnOffTime(Current current) {
        return autoTurnOffTime;
    }
}
