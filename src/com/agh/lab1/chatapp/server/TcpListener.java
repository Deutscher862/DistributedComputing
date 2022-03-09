package com.agh.lab1.chatapp.server;

import java.io.IOException;
import java.net.ServerSocket;

class TcpListener extends Thread {

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(Server.PORT)) {
            while (!serverSocket.isClosed()) {
                TcpClientHandler clientHandler = new TcpClientHandler(serverSocket.accept());
                Server.executor.submit(clientHandler);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
