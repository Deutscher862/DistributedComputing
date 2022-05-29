package main;

import SmartHome.Color;
import SmartHome.DeviceInfo;
import SmartHome.NightMode;
import SmartHome.ThermostatState;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import devices.*;

import java.util.logging.Level;

class Server2 {
    public static void main(String[] args) {
        DeviceList deviceList = new DeviceList();

        OutdoorLight outdoorLight = new OutdoorLight(new DeviceInfo(4, "lamp"), new NightMode());
        Thermostat thermostat = new Thermostat(new DeviceInfo(5, "thermostat2"), new ThermostatState());
        LightBulb lightBulb = new LightBulb(new DeviceInfo(6, "lightbulb"));

        deviceList.addDevice(outdoorLight.getDeviceInfo(null));
        deviceList.addDevice(thermostat.getDeviceInfo(null));
        deviceList.addDevice(lightBulb.getDeviceInfo(null));

        ServerLogger.log(Level.INFO, "Starting server...");

        int status = 0;
        Communicator communicator = null;

        try {
            communicator = Util.initialize(args);

            ObjectAdapter adapter = communicator.createObjectAdapter("Adapter2");

            adapter.add(deviceList, new Identity("deviceList2", "list"));
            adapter.add(outdoorLight, new Identity("lamp", "device"));
            adapter.add(thermostat, new Identity("thermostat2", "device"));
            adapter.add(lightBulb, new Identity("lightbulb", "device"));

            adapter.activate();

            ServerLogger.log(Level.INFO, "Start listening on port 10001");
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
