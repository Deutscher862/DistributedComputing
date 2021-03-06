package sr.grpc.server;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class grpcServer {
    private static final Logger logger = Logger.getLogger(grpcServer.class.getName());
    private static final int PORT = 50051;

    private Server server;

    private void start() throws IOException {
        server = NettyServerBuilder.forPort(PORT).executor(Executors.newFixedThreadPool(16))
                .addService(new CalculatorImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + PORT);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            grpcServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final grpcServer server = new grpcServer();
        server.start();
        server.blockUntilShutdown();
    }
}
