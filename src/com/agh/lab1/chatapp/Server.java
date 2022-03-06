package com.agh.lab1.chatapp;

import java.util.ArrayList;
import java.util.List;

class Server {
    public static void main(String[] args) {
        System.out.println("JAVA TCP SERVER");
        int noOpenPorts = 2;
        List<PortListener> openPorts = new ArrayList<>();
        MessageNotificator messageNotificator = new MessageNotificator();

        for (int i = 0; i < noOpenPorts; i++) {
            PortListener portListener = new PortListener(i + 12345, messageNotificator);
            messageNotificator.addObserver(portListener);
            openPorts.add(portListener);
            portListener.start();
        }

//        System.out.println("JAVA TCP SERVER");
//        int portNumber = 12345;
//
//        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
//            while (true) {
//                // accept client
//                Socket clientSocket = serverSocket.accept();
//                System.out.println("client connected");
//
//                // in & out streams
//                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//
//                // read msg, send response
//                String msg = in.readLine();
//                System.out.println("received msg: " + msg);
//                out.println("Pong Java Tcp");
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
