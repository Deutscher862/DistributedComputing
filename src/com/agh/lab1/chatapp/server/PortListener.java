package com.agh.lab1.chatapp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class PortListener extends Thread {
    private final int portNumber;
    private String currentClient;
    private final MessageNotificator messageNotificator;
    private final List<String> messagesToSent = new ArrayList<>();

    PortListener(int portNumber, MessageNotificator messageNotificator) {
        this.portNumber = portNumber;
        this.messageNotificator = messageNotificator;
    }

    void addMessage(String msg) {
        messagesToSent.add(msg);
    }

    String getUserFromMessage(String msg) {
        return msg.split(":")[0];
    }

    @Override
    public void run() {
        System.out.println("Port " + portNumber + " starts");
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (true) {
                // accept client
                Socket clientSocket = serverSocket.accept();

                // in stream
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                //add message to buffer
                String receivedMessage = in.readLine();
                currentClient = getUserFromMessage(receivedMessage);
                messageNotificator.notifyOthers(receivedMessage);

                if (!messagesToSent.isEmpty()) {
                    String msg = messagesToSent.remove(0);
                    if (!getUserFromMessage(msg).equals(currentClient))
                        out.println(msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
