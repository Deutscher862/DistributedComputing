["java:package:devices"]
module SmartHome {
    interface Device {
        idempotent string getName();
        void turnOn();
        void turnOff();
    }
}
