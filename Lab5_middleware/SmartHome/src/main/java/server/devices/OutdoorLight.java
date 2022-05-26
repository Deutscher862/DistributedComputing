package server.devices;

import SmartHome.DeviceInfo;
import SmartHome.NightMode;
import com.zeroc.Ice.Current;

public class OutdoorLight extends LightBulb implements SmartHome.OutdoorLight {
    private NightMode nightMode;

    public OutdoorLight(DeviceInfo info, NightMode nightMode) {
        super(info);
        this.nightMode = nightMode;
        this.nightMode.nightModeEnabled = false;
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
