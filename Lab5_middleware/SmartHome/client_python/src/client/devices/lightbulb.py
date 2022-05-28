from devices import device_handler_loop


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
    color = input("new color: ")
    lightbulb.setColor(color)
    get_color(lightbulb)


operations = {
    "info": get_device_info,
    "turnOn": turn_on,
    "turnOff": turn_off,
    "getColor": get_color,
    "setColor": set_color,
    "getState": get_lightbulb_state,
    "exit": None
}


def lightbulb_handler(lightbulb, device_name):
    device_handler_loop.run_loop(operations, lightbulb, device_name)
