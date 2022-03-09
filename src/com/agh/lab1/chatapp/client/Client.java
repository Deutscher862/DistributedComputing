package com.agh.lab1.chatapp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

class Client {
    static String nick;
    final static int PORT = 12345;
    static String hostName = "localhost";
    static Socket socket;
    static PrintWriter tcpOut;

    public static void main(String[] args) {
        System.out.println("JAVA CLIENT");

        System.out.print("Enter your nick: ");
        Scanner scanner = new Scanner(System.in);
        nick = scanner.nextLine();

        try {
            socket = new Socket(hostName, PORT);
            startTcpSocket();
            UdpDatagram udpDatagram = new UdpDatagram(socket, nick);
            startUdpSocket(udpDatagram);

            while (true) {
                Scanner scan = new Scanner(System.in);
                String msg = scan.nextLine();

                if (msg.equals("U")) {
                    udpDatagram.send();
                } else {
                    tcpOut.println(msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startTcpSocket() throws IOException {
        tcpOut = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        TcpListener tcpListener = new TcpListener(in);
        tcpListener.start();
        tcpOut.println(nick);
    }

    public static void startUdpSocket(UdpDatagram udpConnection) {
        UdpListener udpListener = new UdpListener(udpConnection.getDatagramSocket());
        udpListener.start();
    }

}
