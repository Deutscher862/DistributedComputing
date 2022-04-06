package mountainshop.supplier;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

class TopicWriter {
    private final String exchangeName;
    private Channel channel;

    public TopicWriter(String exchangeName) {
        this.exchangeName = exchangeName;
        establishConnection();
    }

    private void establishConnection() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void send(String message, String topic) throws IOException {
        channel.basicPublish(exchangeName, topic, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println("Wyslano: " + message);
    }
}
