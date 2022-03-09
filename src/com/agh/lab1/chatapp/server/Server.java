package com.agh.lab1.chatapp.server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Server {
    public final static int PORT = 12345;
    public static final ExecutorService executor = Executors.newFixedThreadPool(100);
    static final Map<String, TcpClientHandler> users = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("JAVA TCP SERVER");
        TcpListener tcpListener = new TcpListener();
        tcpListener.start();
    }

    public static void addUser(String nick, TcpClientHandler tcpClientHandler) {
        System.out.println("Adding " + nick);
        users.put(nick, tcpClientHandler);
    }

    public static void sendMessages(String nick, String msg) {
        synchronized (users) {
            for (Map.Entry<String, TcpClientHandler> user : users.entrySet()) {
                if (!user.getKey().equals(nick))
                    user.getValue().send(nick + ": " + msg);
            }
        }
    }
}