module SmartHome {
    enum Color { White, Red, Green, Blue };

    struct DeviceInfo {
        int id;
        string name;
    };

    struct LightbulbState {
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

    interface Lightbulb {
        idempotent DeviceInfo getDeviceInfo();
        idempotent LightbulbState getLightbulbState();
        idempotent Color getColor() throws DeviceTurnedOffError;
        void setColor(Color newColor) throws DeviceTurnedOffError, InvalidColorError;
        void turnOn();
        void turnOff();
    };

    interface OutdoorLight extends Lightbulb {
        void setNightMode(NightMode nightMode);
        idempotent NightMode getNightMode();
    };

    interface RoomLight extends Lightbulb {
        void setAutoTurnOffTime(Time time);
        idempotent Time getAutoTurnOffTime();
    };

    struct Temperature {
        int tempareture;
    };

    interface TemperatureSensor {
        idempotent DeviceInfo getDeviceInfo();
        void turnOn();
        void turnOff();
        idempotent Temperature getTemperature() throws DeviceTurnedOffError;
    };

};
