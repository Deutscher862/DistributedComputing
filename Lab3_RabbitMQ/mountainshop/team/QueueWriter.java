package mountainshop.team;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

class QueueWriter {
    private Channel channel;
    private final String key;

    public QueueWriter(String key) {
        this.key = key;
        establishConnection();
    }

    private void establishConnection() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(this.key, false, false, false, null);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) throws IOException {
        channel.basicPublish("", key, null, message.getBytes());
    }
}
