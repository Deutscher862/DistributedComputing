package devices;

import SmartHome.*;
import com.zeroc.Ice.Current;

public class LightBulb implements SmartHome.LightBulb {
    private final DeviceInfo deviceInfo;
    private final LightBulbState lightbulbState;

    public LightBulb(DeviceInfo info) {
        this.deviceInfo = info;
        this.lightbulbState = new LightBulbState();
    }

    @Override
    public DeviceInfo getDeviceInfo(Current current) {
        return deviceInfo;
    }

    @Override
    public LightBulbState getLightBulbState(Current current) {
        return lightbulbState;
    }

    @Override
    public Color getColor(Current current) throws DeviceTurnedOffError {
        if (!lightbulbState.tunredOn) {
            throw new DeviceTurnedOffError("LightBulb " + deviceInfo.name + " is turned off");
        }
        return lightbulbState.color;
    }

    @Override
    public void setColor(Color newColor, Current current) throws DeviceTurnedOffError, InvalidColorError {
        if (!lightbulbState.tunredOn) {
            throw new DeviceTurnedOffError("LightBulb " + deviceInfo.name + " is turned off");
        }
        lightbulbState.color = newColor;
    }

    @Override
    public void turnOn(Current current) {
        lightbulbState.tunredOn = true;
    }

    @Override
    public void turnOff(Current current) {
        lightbulbState.tunredOn = false;
    }
}
