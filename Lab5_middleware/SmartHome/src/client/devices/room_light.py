from devices import lightbulb
from devices import device_handler_loop


def set_auto_turn_off_time(room_light):
    hours = input("new time hours: ")
    minutes = input("new time minutes: ")
    room_light.setHours(hours)
    room_light.setMinutes(minutes)
    get_auto_turn_off_time(room_light)


def get_auto_turn_off_time(room_light):
    print("Current auto turn off time: {}".format(room_light.getAutoTurnOffTime()))


operations = {
    "info": lightbulb.get_device_info,
    "turnOn": lightbulb.turn_on,
    "turnOff": lightbulb.turn_off,
    "getColor": lightbulb.get_color,
    "setColor": lightbulb.set_color,
    "getState": lightbulb.get_lightbulb_state,
    "setAutoTurnOffTime": set_auto_turn_off_time,
    "getAutoTurnOffTime": get_auto_turn_off_time,
    "exit": None,
}


def room_light_handler(room_light, device_name):
    device_handler_loop.run_loop(operations, room_light, device_name)
