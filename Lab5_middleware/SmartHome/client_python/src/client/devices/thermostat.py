from devices import devices_utils


def turn_on(thermostat):
    thermostat.turnOn()


def turn_off(thermostat):
    thermostat.turnOff()


def get_device_info(thermostat):
    print("Device info: {}".format(thermostat.getDeviceInfo()))


def get_state(thermostat):
    print("Current state: {}".format(thermostat.getState()))


def get_temperature(thermostat):
    print("Current temperature: {}".format(thermostat.getTemperature()))


def get_air_moisture(thermostat):
    print("Current air moisture: {}".format(thermostat.getAirMoisture()))


def increase_temperature(thermostat):
    thermostat.increaseTemperature()
    get_temperature(thermostat)


def decrease_temperature(thermostat):
    thermostat.decreaseTemperature()
    get_temperature(thermostat)


def increase_air_moisture(thermostat):
    thermostat.increaseAirMoisture()
    get_air_moisture(thermostat)


def decrease_air_moisture(thermostat):
    thermostat.decreaseAirMoisture()
    get_air_moisture(thermostat)


operations = {
    "info": get_device_info,
    "help": help,
    "turnOn": turn_on,
    "turnOff": turn_off,
    "getState": get_state,
    "getTemperature": get_temperature,
    "getAirMoisture": get_air_moisture,
    "increaseTemperature": increase_temperature,
    "decreaseTemperature": decrease_temperature,
    "increaseAirMoisture": increase_air_moisture,
    "decreaseAirMoisture": decrease_air_moisture,
    "exit": None
}


def thermostat_handler(thermostat, device_name):
    devices_utils.run_loop(operations, thermostat, device_name)
