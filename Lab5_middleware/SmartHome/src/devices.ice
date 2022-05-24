["java:package:devices"]
module SmartHome {
    interface Device {
        idempotent string getName();
        void turnOn();
        void turnOff();
    }

    interface Lightbulb extends Device {
        idempotent Color getColor() throws DeviceStateError;
        void setColor(Color newColor) throws DeviceStateError, InvalidCommandError;
    }

    enum Color { White, Red, Green, Blue }

    exception DeviceStateError {
    		string message;
    }

    exception InvalidCommandError {
        		string message;
    }
}
