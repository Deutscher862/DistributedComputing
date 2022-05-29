from devices import devices_utils

colors = [
    "white",
    "red",
    "green",
    "blue",
]


def turn_on(lightbulb):
    lightbulb.turnOn()


def turn_off(lightbulb):
    lightbulb.turnOff()


def get_color(lightbulb):
    print("Current color: {}".format(lightbulb.getColor()))


def get_lightbulb_state(lightbulb):
    print("Current state: {}".format(lightbulb.getLightBulbState()))


def get_device_info(lightbulb):
    print("Device info: {}".format(lightbulb.getDeviceInfo()))


def set_color(lightbulb):
    print("Available color")
    for color in colors:
        print(color)
    color = input("new color: ")
    lightbulb.setColor(color)
    get_color(lightbulb)


operations = {
    "info": get_device_info,
    "help": help,
    "turnOn": turn_on,
    "turnOff": turn_off,
    "getColor": get_color,
    "setColor": set_color,
    "getState": get_lightbulb_state,
    "exit": None
}


def lightbulb_handler(lightbulb, device_name):
    devices_utils.run_loop(operations, lightbulb, device_name)
