package lab1gniazda.zad3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class JavaUdpServer {

    public static void main(String[] args) {
        System.out.println("JAVA UDP SERVER");

        int portNumber = 9011;
        try (DatagramSocket socket = new DatagramSocket(portNumber)) {
            byte[] receiveBuffer = new byte[1024];

            while (true) {
                Arrays.fill(receiveBuffer, (byte) 0);
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);


                int nb = ByteBuffer.wrap(receivePacket.getData()).order(ByteOrder.LITTLE_ENDIAN).getInt();
                System.out.println("received number: " + nb);
                nb++;
                var increasedBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(nb).array();
                DatagramPacket sendPacket = new DatagramPacket(increasedBuffer, increasedBuffer.length, receivePacket.getSocketAddress());
                socket.send(sendPacket);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}