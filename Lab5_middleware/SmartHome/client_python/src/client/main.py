import sys, Ice

from SmartHome import LightBulbPrx, RoomLightPrx, OutdoorLightPrx, DeviceListPrx, ThermostatPrx, DeviceTurnedOffError
from devices.lightbulb import lightbulb_handler
from devices.room_light import room_light_handler
from devices.outdoor_light import outdoor_light_handler
from devices.thermostat import thermostat_handler

devices = {
    "room1": RoomLightPrx,
    "room2": RoomLightPrx,
    "lamp": OutdoorLightPrx,
    "thermostat": ThermostatPrx,
    "lightbulb": LightBulbPrx
}


def list_devices(communicator):
    base = communicator.propertyToProxy(f"""deviceList.Proxy""")
    list = DeviceListPrx.checkedCast(base)
    device_list = list.getDevicesList()

    print('Available devices')
    for device in device_list:
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
    elif device_name == "thermostat":
        thermostat_handler(device, device_name)


def init_message():
    print("Client start")
    print("Input \"list\" to list available devices")
    print("Input device_name to connect to it\n")


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

            except DeviceTurnedOffError as e:
                print('Invalid operation: ', e.reason)
                print()
            except Exception as e:
                print(e)
                print()

#TODO wyjątki w pythonie, dwa serwery, nazwa serwera w device info, zmiana nazwy w nightmode
if __name__ == "__main__":
    main()
