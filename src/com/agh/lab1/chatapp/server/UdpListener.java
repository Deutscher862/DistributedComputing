package com.agh.lab1.chatapp.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

class UdpListener extends Thread {
    @Override
    public void run() {
        try (DatagramSocket datagramSocket = new DatagramSocket(Server.PORT)) {
            while (!datagramSocket.isClosed()) {
                byte[] bytes = new byte[500];
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                datagramSocket.receive(datagramPacket);
                String message = new String(bytes);
                String[] split = message.split(":", 2);
                String nick = split[0];
                System.out.println("TO WCZYTAŁEM: " + nick);
                Server.sendUdpMessage(nick, bytes, datagramSocket);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
