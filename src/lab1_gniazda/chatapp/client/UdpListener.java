package lab1_gniazda.chatapp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

class UdpListener extends Thread {
    private final DatagramSocket datagramSocket;

    public UdpListener(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run() {
        try {
            while (!datagramSocket.isClosed()) {
                byte[] buffer = new byte[500];
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                String received = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("UDP " + received);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            datagramSocket.close();
        }
    }
}
