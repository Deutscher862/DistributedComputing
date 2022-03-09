package com.agh.lab1.chatapp.client;

import java.io.IOException;
import java.net.*;

class UdpDatagram {
    private final DatagramSocket datagramSocket;
    private final InetAddress IPAddress;
    private final byte[] msgBytes;

    UdpDatagram(Socket socket, String nick) throws SocketException, UnknownHostException {
        datagramSocket = new DatagramSocket(socket.getLocalPort());
        IPAddress = InetAddress.getByName("localhost");

        msgBytes =(nick + ":\n" +
                "   __\n" +
                "o-''|\\_____/)\n" +
                " \\_/|_)     )\n" +
                "    \\  __  /\n" +
                "    (_/ (_/\n").getBytes();
    }

    void send() throws IOException {
        datagramSocket.send(new DatagramPacket(msgBytes, msgBytes.length, IPAddress, Client.PORT));
    }

    public DatagramSocket getDatagramSocket() {
        return datagramSocket;
    }
}
