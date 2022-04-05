package mountainshop.team;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

class QueueWriter {
    private final String topic;
    private final String exchangeName;
    private Channel channel;

    QueueWriter(String exchange_name, String topic) {
        this.topic = topic;
        this.exchangeName = exchange_name;

        establishConnection();
    }

    void send(String message) {
        try {
            channel.basicPublish(exchangeName, topic, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void establishConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            this.channel = connection.createChannel();
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
