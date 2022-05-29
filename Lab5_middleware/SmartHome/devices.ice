module SmartHome {
    enum Color { WHITE, RED, GREEN, BLUE };

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

    exception ValueLimitReachedError {
        string errorMessage;
    };

    interface LightBulb {
        idempotent DeviceInfo getDeviceInfo();
        idempotent LightBulbState getLightBulbState();
        idempotent Color getColor() throws DeviceTurnedOffError;
        void setColor(string newColor) throws DeviceTurnedOffError, InvalidColorError;
        void turnOn();
        void turnOff();
    };

    interface OutdoorLight extends LightBulb {
        void setNightMode(NightMode nightMode) throws DeviceTurnedOffError;
        idempotent NightMode getNightMode() throws DeviceTurnedOffError;
    };

    interface RoomLight extends LightBulb {
        void setAutoTurnOffTime(Time time) throws DeviceTurnedOffError;
        idempotent Time getAutoTurnOffTime() throws DeviceTurnedOffError;
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
        idempotent ThermostatState getState() throws DeviceTurnedOffError;
        idempotent Temperature getTemperature() throws DeviceTurnedOffError;
        void increaseTemperature() throws DeviceTurnedOffError, ValueLimitReachedError;
        void decreaseTemperature() throws DeviceTurnedOffError, ValueLimitReachedError;
        idempotent AirMoisture getAirMoisture() throws DeviceTurnedOffError;
        void increaseAirMoisture() throws DeviceTurnedOffError, ValueLimitReachedError;
        void decreaseAirMoisture() throws DeviceTurnedOffError, ValueLimitReachedError;
    };

    sequence<string> devices;

    interface DeviceList {
        idempotent devices getDevicesList();
    };

};
