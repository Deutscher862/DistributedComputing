package com.agh.lab1.chatapp;

import java.util.ArrayList;
import java.util.List;

class MessageNotificator {
    private final List<PortListener> observers = new ArrayList<>();

    void addObserver(PortListener portListener) {
        observers.add(portListener);
    }

    void notifyOthers(String message) {
        System.out.println("Adding message: " + message);
        for (PortListener portListener : observers) {
            portListener.addMessage(message);
        }
    }
}
