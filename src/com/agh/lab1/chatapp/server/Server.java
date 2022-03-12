package com.agh.lab1.chatapp.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

class Server {
    final static int PORT = 12345;
    final static String LOCALHOST = "localhost";
    final static Map<String, TcpClientHandler> USERS = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("JAVA SERVER STARTING");
        TcpListener tcpListener = new TcpListener();
        tcpListener.start();
        UdpListener udpListener = new UdpListener();
        udpListener.start();
    }

    static void addUser(String nick, TcpClientHandler tcpClientHandler) {
        USERS.put(nick, tcpClientHandler);
        System.out.println(nick + " connected");
    }

    static void removeUser(String nick) {
        USERS.remove(nick);
        System.out.println(nick + " disconnected");
    }

    static void sendTcpMessage(String nick, String msg) {
        synchronized (USERS) {
            for (Map.Entry<String, TcpClientHandler> user : USERS.entrySet()) {
                if (!user.getKey().equals(nick))
                    user.getValue().send(nick + ": " + msg);
            }
        }
    }

    static void sendUdpMessage(String nick, byte[] msg, DatagramSocket datagramSocket) {
        synchronized (Server.USERS) {
            for (Map.Entry<String, TcpClientHandler> user : USERS.entrySet()) {
                if (!user.getKey().equals(nick)) {
                    try {
                        int port = user.getValue().getSocket().getPort();
                        InetAddress IPAddress = InetAddress.getByName(LOCALHOST);
                        datagramSocket.send(new DatagramPacket(msg, msg.length, IPAddress, port));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
