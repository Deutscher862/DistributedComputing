package com.agh.lab1.chatapp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

class MulticastListener extends Thread {

    @Override
    public void run() {
        try (MulticastSocket multicastSocket = new MulticastSocket(Client.MULTICAST_PORT)) {
            multicastSocket.joinGroup(InetAddress.getByName(Client.MULTICAST_IP));
            while (!multicastSocket.isClosed()) {
                byte[] buff = new byte[500];
                DatagramPacket datagramPacket = new DatagramPacket(buff, buff.length);
                multicastSocket.receive(datagramPacket);
                String msg = new String(buff).replaceAll("0", "\0");
                if (!msg.startsWith(Client.nick)) {
                    System.out.println("MultiCast UDP " + msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
