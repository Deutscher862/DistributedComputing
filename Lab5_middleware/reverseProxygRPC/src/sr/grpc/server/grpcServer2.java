package sr.grpc.server;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class grpcServer2 {
    private static final Logger logger = Logger.getLogger(grpcServer2.class.getName());
    private static final int PORT = 50052;

    private Server server;

    private void start() throws IOException {
        server = NettyServerBuilder.forPort(PORT).executor(Executors.newFixedThreadPool(16))
                .addService(new AdvancedCalculatorImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + PORT);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            grpcServer2.this.stop();
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
        final grpcServer2 server = new grpcServer2();
        server.start();
        server.blockUntilShutdown();
    }
}
