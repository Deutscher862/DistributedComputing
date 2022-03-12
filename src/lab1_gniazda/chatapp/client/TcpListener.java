package lab1_gniazda.chatapp.client;

import java.io.BufferedReader;
import java.io.IOException;

class TcpListener extends Thread {
    private final BufferedReader in;

    TcpListener(BufferedReader reader) {
        this.in = reader;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String response = in.readLine();
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
