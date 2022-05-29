from devices import lightbulb
from devices import devices_utils
from SmartHome import NightMode


def set_night_mode(outdoor_light):
    msg = input("enable night mode? yes/no: ")
    night_mode = NightMode()
    if msg.upper() == "YES":
        night_mode.nightModeEnabled = True
    elif msg.upper() == "NO":
        night_mode.nightModeEnabled = False
    else:
        print("Invalid input")
        return

    outdoor_light.setNightMode(night_mode)
    get_night_mode(outdoor_light)


def get_night_mode(outdoor_light):
    print("Current night mode status: {}".format(outdoor_light.getNightMode()))


operations = {
    "info": lightbulb.get_device_info,
    "help": help,
    "turnOn": lightbulb.turn_on,
    "turnOff": lightbulb.turn_off,
    "getColor": lightbulb.get_color,
    "setColor": lightbulb.set_color,
    "getState": lightbulb.get_lightbulb_state,
    "setNightMode": set_night_mode,
    "getNightMode": get_night_mode,
    "exit": None,
}


def outdoor_light_handler(outdoor_light, device_name):
    devices_utils.run_loop(operations, outdoor_light, device_name)
