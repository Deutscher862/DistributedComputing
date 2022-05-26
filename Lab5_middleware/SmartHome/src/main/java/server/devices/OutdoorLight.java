package server.devices;

import SmartHome.DeviceInfo;
import SmartHome.NightMode;
import com.zeroc.Ice.Current;

class OutdoorLight extends LightBulb implements SmartHome.OutdoorLight {
    private NightMode nightMode;

    OutdoorLight(DeviceInfo info) {
        super(info);
    }

    @Override
    public void setNightMode(NightMode nightMode, Current current) {
        this.nightMode = nightMode;
    }

    @Override
    public NightMode getNightMode(Current current) {
        return nightMode;
    }
}
