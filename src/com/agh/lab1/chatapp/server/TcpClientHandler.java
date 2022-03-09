package com.agh.lab1.chatapp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class TcpClientHandler extends Thread {
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;

    public TcpClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String nick = in.readLine();
            Server.addUser(nick, this);
            while (true) {
                String msg = in.readLine();
                Server.sendMessages(nick, msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg) {
        out.println(msg);
    }
}