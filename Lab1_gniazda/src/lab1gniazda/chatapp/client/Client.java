package lab1gniazda.chatapp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

class Client {
    static String nick;
    final static int PORT = 12345;
    final static String LOCALHOST = "localhost";
    final static String MULTICAST_IP = "224.1.1.1";
    final static int MULTICAST_PORT = 12346;
    static Socket socket;
    static PrintWriter tcpOut;

    public static void main(String[] args) {
        System.out.println("JAVA CLIENT STARTING");
        System.out.print("Enter your nick: ");
        Scanner scanner = new Scanner(System.in);
        nick = scanner.nextLine();

        try {
            socket = new Socket(LOCALHOST, PORT);
            startTcpListener();
            lab1gniazda.chatapp.client.UdpDatagram udpDatagram = new lab1gniazda.chatapp.client.UdpDatagram(socket, nick);
            startUdpListener(udpDatagram);
            startMulticastListener();

            while (true) {
                Scanner scan = new Scanner(System.in);
                String msg = scan.nextLine();

                if (msg.equals("U")) {
                    udpDatagram.send(LOCALHOST, PORT);
                } else if (msg.equals("M")) {
                    udpDatagram.send(MULTICAST_IP, MULTICAST_PORT);
                } else {
                    tcpOut.println(msg);
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static void startTcpListener() throws IOException {
        tcpOut = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        TcpListener tcpListener = new TcpListener(in);
        tcpListener.start();
        tcpOut.println(nick);
    }

    public static void startUdpListener(UdpDatagram udpConnection) {
        UdpListener udpListener = new UdpListener(udpConnection.getDatagramSocket());
        udpListener.start();
    }

    public static void startMulticastListener() {
        MulticastListener multicastListener = new MulticastListener();
        multicastListener.start();
    }
}
