package devices;

import SmartHome.DeviceInfo;
import com.zeroc.Ice.Current;
import main.ServerLogger;

import java.util.logging.Level;

public class DeviceList implements SmartHome.DeviceList {
    private final String[] devices = new String[10];
    private int no_devices = 0;

    @Override
    public String[] getDevicesList(Current current) {
        ServerLogger.log(Level.INFO, "Returning device list");
        return devices;
    }

    public void addDevice(DeviceInfo deviceInfo) {
        ServerLogger.log(Level.INFO, "Add new device to list: " + deviceInfo.name);
        devices[no_devices] = deviceInfo.id + ": " + deviceInfo.name;
        no_devices += 1;
    }
}
