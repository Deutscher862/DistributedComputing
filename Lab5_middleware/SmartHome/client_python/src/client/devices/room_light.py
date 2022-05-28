from devices import lightbulb
from devices import device_handler_loop
from SmartHome import Time


def set_auto_turn_off_time(room_light):
    time = Time()
    time.hours = int(input("new time hours: "))
    time.minutes = int(input("new time minutes: "))
    room_light.setAutoTurnOffTime(time)
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
    "setTime": set_auto_turn_off_time,
    "getTime": get_auto_turn_off_time,
    "exit": None,
}


def room_light_handler(room_light, device_name):
    device_handler_loop.run_loop(operations, room_light, device_name)
