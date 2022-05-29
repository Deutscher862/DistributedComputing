import Ice
import sys

from SmartHome import LightBulbPrx, RoomLightPrx, OutdoorLightPrx, DeviceListPrx, ThermostatPrx
from devices.lightbulb import lightbulb_handler
from devices.outdoor_light import outdoor_light_handler
from devices.room_light import room_light_handler
from devices.thermostat import thermostat_handler

devices = {
    "room1": RoomLightPrx,
    "room2": RoomLightPrx,
    "lamp": OutdoorLightPrx,
    "thermostat1": ThermostatPrx,
    "thermostat2": ThermostatPrx,
    "lightbulb": LightBulbPrx
}


def list_devices(communicator):
    base = communicator.propertyToProxy(f"""deviceList1.Proxy""")
    list1 = DeviceListPrx.checkedCast(base)
    device_list1 = list1.getDevicesList()

    base = communicator.propertyToProxy(f"""deviceList2.Proxy""")
    list2 = DeviceListPrx.checkedCast(base)
    device_list2 = list2.getDevicesList()

    print('Available devices:')
    for device in device_list1 + device_list2:
        if device != "":
            print(device)
    print()


def handle_device(device_name, device_prx, communicator):
    base = communicator.propertyToProxy(f"""{device_name}.Proxy""")
    device = device_prx.checkedCast(base)
    if device_name == "lightbulb":
        lightbulb_handler(device, device_name)
    elif device_name == "room1" or device_name == "room2":
        room_light_handler(device, device_name)
    elif device_name == "lamp":
        outdoor_light_handler(device, device_name)
    elif device_name == "thermostat1" or device_name == "thermostat2":
        thermostat_handler(device, device_name)


def init_message():
    print("Client starts...")
    print("Input \"list\" to list available devices")
    print("Input device name to connect to it\n")


def main():
    with Ice.initialize(sys.argv) as communicator:
        init_message()
        while True:
            try:
                message = input("SmartHome> ")

                if message == "list":
                    list_devices(communicator)
                else:
                    device_prx = devices[message]
                    handle_device(message, device_prx, communicator)
            except Exception as e:
                print(e)
                print()


if __name__ == "__main__":
    main()
