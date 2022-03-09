package com.agh.lab1.chatapp.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class Client {
    public static void main(String[] args) {
        System.out.println("JAVA TCP CLIENT");
        String hostName = "localhost";
        String nick = "Maciek123";
        int portNumber = 12345;

        try (Socket socket = new Socket(hostName, portNumber)) {
            while (true) {
                // in & out streams
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // send msg, read response
                System.out.print("msg>");
                Scanner scan = new Scanner(System.in);
                String msg = scan.nextLine();
                //TODO split input and output into 2 threads
                out.println(nick + ": " + msg);
                String response = in.readLine();
                System.out.println(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
