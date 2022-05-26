package devices;

import SmartHome.DeviceInfo;
import com.zeroc.Ice.Current;

public class DeviceList implements SmartHome.DeviceList {
    private final String[] devices = new String[10];
    private int no_devices = 0;

    @Override
    public String[] getDevicesList(Current current) {
        return devices;
    }

    public void addDevice(DeviceInfo deviceInfo) {
        devices[no_devices] = deviceInfo.id + ": " + deviceInfo.name;
        no_devices += 1;
    }
}
