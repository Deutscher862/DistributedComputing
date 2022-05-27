module SmartHome {
    enum Color { White, Red, Green, Blue };

    struct DeviceInfo {
        int id;
        string name;
    };

    struct LightBulbState {
        Color color;
        bool tunredOn;
    };

    struct Time {
        int hours;
        int minutes;
    };

    struct NightMode {
        bool nightModeEnabled;
    };

    exception DeviceTurnedOffError {
        string errorMessage;
    };

    exception InvalidColorError {
        string errorMessage;
    };

    interface LightBulb {
        idempotent DeviceInfo getDeviceInfo();
        idempotent LightBulbState getLightBulbState();
        idempotent Color getColor() throws DeviceTurnedOffError;
        void setColor(Color newColor) throws DeviceTurnedOffError, InvalidColorError;
        void turnOn();
        void turnOff();
    };

    interface OutdoorLight extends LightBulb {
        void setNightMode(bool nightModeEnabled);
        idempotent NightMode getNightMode();
    };

    interface RoomLight extends LightBulb {
        void setHours(int hours);
        void setMinutes(int minutes);
        idempotent Time getAutoTurnOffTime();
    };

    struct Temperature {
        float value;
    };

    struct AirMoisture {
        float value;
    };

    struct ThermostatState {
        Temperature temperature;
        AirMoisture airMisture;
        bool turnedOn;
    };

    interface Thermostat {
        idempotent DeviceInfo getDeviceInfo();
        void turnOn();
        void turnOff();
        idempotent Temperature getTemperature() throws DeviceTurnedOffError;
        void increaseTemperature() throws DeviceTurnedOffError;
        void decreaseTemperature() throws DeviceTurnedOffError;
        idempotent AirMoisture getAirMoisture() throws DeviceTurnedOffError;
        void increaseAirMoisture() throws DeviceTurnedOffError;
        void decreaseAirMoisture() throws DeviceTurnedOffError;
    };

    sequence<string> devices;

    interface DeviceList {
        idempotent devices getDevicesList();
    };

};
