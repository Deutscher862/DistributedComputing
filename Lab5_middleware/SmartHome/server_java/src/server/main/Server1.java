package main;

import SmartHome.DeviceInfo;
import SmartHome.ThermostatState;
import SmartHome.Time;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import devices.*;

import java.util.logging.Level;

class Server1 {
    public static void main(String[] args) {
        DeviceList deviceList = new DeviceList();

        RoomLight roomLight1 = new RoomLight(new DeviceInfo(1, "room1"), new Time(12, 30));
        RoomLight roomLight2 = new RoomLight(new DeviceInfo(2, "room2"), new Time(20, 15));
        Thermostat thermostat = new Thermostat(new DeviceInfo(3, "thermostat1"), new ThermostatState());

        deviceList.addDevice(roomLight1.getDeviceInfo(null));
        deviceList.addDevice(roomLight2.getDeviceInfo(null));
        deviceList.addDevice(thermostat.getDeviceInfo(null));

        ServerLogger.log(Level.INFO, "Starting server...");

        int status = 0;
        Communicator communicator = null;

        try {
            communicator = Util.initialize(args);

            ObjectAdapter adapter = communicator.createObjectAdapter("Adapter1");

            adapter.add(deviceList, new Identity("deviceList1", "list"));
            adapter.add(roomLight1, new Identity("room1", "device"));
            adapter.add(roomLight2, new Identity("room2", "device"));
            adapter.add(thermostat, new Identity("thermostat1", "device"));

            adapter.activate();

            ServerLogger.log(Level.INFO, "Start listening on port 10000");
            communicator.waitForShutdown();

        } catch (Exception e) {
            ServerLogger.log(Level.SEVERE, "Server error");
            e.printStackTrace();
            status = 1;
        }
        if (communicator != null) {
            try {
                communicator.destroy();
            } catch (Exception e) {
                ServerLogger.log(Level.SEVERE, "Communicator destroying failed");
                e.printStackTrace();
                status = 1;
            }
        }
        System.exit(status);
    }
}
