package lab1_gniazda.chatapp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

class MulticastListener extends Thread {

    @Override
    public void run() {
        try (MulticastSocket multicastSocket = new MulticastSocket(Client.MULTICAST_PORT)) {
            multicastSocket.joinGroup(InetAddress.getByName(Client.MULTICAST_IP));
            while (!multicastSocket.isClosed()) {
                byte[] buffer = new byte[500];
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                multicastSocket.receive(datagramPacket);
                String msg = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                if (!msg.startsWith(Client.nick)) {
                    System.out.println("MultiCast UDP " + msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
