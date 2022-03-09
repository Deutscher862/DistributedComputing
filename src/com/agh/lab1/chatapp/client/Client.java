package com.agh.lab1.chatapp.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class Client {
    static String nick;
    final static int PORT = 12345;
    static String hostName = "localhost";

    public static void main(String[] args) {
        System.out.println("JAVA CLIENT");

        System.out.print("Enter your nick: ");
        Scanner scanner = new Scanner(System.in);
        nick = scanner.nextLine();

        startSocket();
    }

    public static void startSocket() {
        try (Socket socket = new Socket(hostName, PORT)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            TcpListener tcpListener = new TcpListener(in);
            tcpListener.start();
            out.println(nick);

            while (true) {
                Scanner scan = new Scanner(System.in);
                out.println(scan.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
