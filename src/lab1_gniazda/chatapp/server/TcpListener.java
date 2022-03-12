package lab1_gniazda.chatapp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TcpListener extends Thread {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(Server.PORT)) {
            while (!serverSocket.isClosed()) {
                TcpClientHandler clientHandler = new TcpClientHandler(serverSocket.accept());
                executorService.submit(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
