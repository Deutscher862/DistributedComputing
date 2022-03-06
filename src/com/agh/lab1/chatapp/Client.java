package com.agh.lab1.chatapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Client {
    public static void main(String[] args) {
        System.out.println("JAVA TCP CLIENT");
        String hostName = "localhost";
        String nick = "Maciek123";
        int portNumber = 12345;

        try (Socket socket = new Socket(hostName, portNumber)) {
            // in & out streams
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // send msg, read response
            out.println(nick + ": hello");
            String response = in.readLine();
            System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
