package main;

import SmartHome.DeviceInfo;
import SmartHome.NightMode;
import SmartHome.Time;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import devices.DeviceList;
import devices.OutdoorLight;
import devices.RoomLight;

import java.util.logging.Level;

class Server1 {
    public static void main(String[] args) {
        DeviceList deviceList = new DeviceList();

        RoomLight roomLight = new RoomLight(new DeviceInfo(1, "Kuchnia"), new Time(12, 30));
        OutdoorLight outdoorLight = new OutdoorLight(new DeviceInfo(2, "Ogródek"), new NightMode());

        deviceList.addDevice(roomLight.getDeviceInfo(null));
        deviceList.addDevice(outdoorLight.getDeviceInfo(null));

        ServerLogger.log(Level.INFO, "Starting server...");

        int status = 0;
        Communicator communicator = null;

        try {
            communicator = Util.initialize(args);

            ObjectAdapter adapter = communicator.createObjectAdapter("Adapter1");

//          ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Adapter1", "tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z");

            adapter.add(deviceList, new Identity("deviceList", "list"));
            adapter.add(roomLight, new Identity("kuchnia", "roomLight"));
            adapter.add(outdoorLight, new Identity("ogródek", "outdoorLight"));

            adapter.activate();

            communicator.waitForShutdown();

        } catch (Exception e) {
            System.err.println(e);
            status = 1;
        }
        if (communicator != null) {
            try {
                communicator.destroy();
            } catch (Exception e) {
                System.err.println(e);
                status = 1;
            }
        }
        System.exit(status);
    }
}
