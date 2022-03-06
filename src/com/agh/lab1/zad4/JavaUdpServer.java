package com.agh.lab1.zad4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class JavaUdpServer {

    public static void main(String[] args) {
        System.out.println("JAVA UDP SERVER");

        int portNumber = 9012;
        try (DatagramSocket socket = new DatagramSocket(portNumber)) {
            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer = new byte[1024];

            while (true) {
                Arrays.fill(receiveBuffer, (byte) 0);
                Arrays.fill(sendBuffer, (byte) 0);
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                String msg = new String(receivePacket.getData());
                System.out.println("received msg: " + msg);

                if (msg.startsWith("Java"))
                    sendBuffer = "Pong Java".getBytes();
                else if (msg.startsWith("Python"))
                    sendBuffer = "Pong Python".getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, receivePacket.getAddress(), receivePacket.getPort());
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
