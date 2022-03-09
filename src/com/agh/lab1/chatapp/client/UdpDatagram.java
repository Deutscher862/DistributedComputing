package com.agh.lab1.chatapp.client;

import java.io.IOException;
import java.net.*;

class UdpDatagram {
    private final DatagramSocket datagramSocket;
    private final byte[] msgBytes;

    UdpDatagram(Socket socket, String nick) throws SocketException, UnknownHostException {
        datagramSocket = new DatagramSocket(socket.getLocalPort());

        msgBytes =(nick + ":\n" +
                "   __\n" +
                "o-''|\\_____/)\n" +
                " \\_/|_)     )\n" +
                "    \\  __  /\n" +
                "    (_/ (_/\n").getBytes();
    }

    void send(String address, int port) throws IOException {
        InetAddress IPAddress = InetAddress.getByName(address);
        datagramSocket.send(new DatagramPacket(msgBytes, msgBytes.length, IPAddress, port));
    }

    public DatagramSocket getDatagramSocket() {
        return datagramSocket;
    }
}
