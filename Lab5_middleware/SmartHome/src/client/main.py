import sys, Ice

from SmartHome import RoomLightPrx, OutdoorLightPrx, DeviceListPrx, ThermostatPrx, DeviceTurnedOffError

devices = {
    "room1": RoomLightPrx,
    "room2": RoomLightPrx,
    "lamp": OutdoorLightPrx,
    "thermostat": ThermostatPrx
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


def init_message():
    print("Client start")
    print("Input \"list\" to list available devices")
    print("Input device_identity to connect to it\n")


def main():
    with Ice.initialize(sys.argv) as communicator:
        init_message()
        while True:
            try:
                message = input("SmartHome> ")

                if message == "list":
                    list_devices(communicator)

                # base = communicator.propertyToProxy(f"""{device_identity}.Proxy""")

                # fridgePrx = LightBulbPrx.checkedCast(base)
                # LightBulbPrx.turnOn()

            except DeviceTurnedOffError as e:
                print('Invalid operation: ', e.reason)
                print()
            except Exception as e:
                print(e)
                print()


if __name__ == "__main__":
    main()
