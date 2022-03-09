package com.agh.lab1.chatapp.server;

import java.util.ArrayList;
import java.util.List;

class MessageNotificator {
    private final List<PortListener> observers = new ArrayList<>();

    void addObserver(PortListener portListener) {
        observers.add(portListener);
    }

    void notifyOthers(String message) {
        System.out.println("Sending message: " + message);
        for (PortListener portListener : observers) {
            portListener.addMessage(message);
        }
    }
}
