package mountainshop.supplier;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

class QueueListener extends Thread {
    private final String topic;
    private final String exchangeName;

    QueueListener(String topic, String exchangeName) {
        this.topic = topic;
        this.exchangeName = exchangeName;
    }

    @Override
    public void run() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, exchangeName, topic);

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                    String message = new String(body, StandardCharsets.UTF_8);
                    System.out.println("Received: " + message);
                }
            };
            System.out.println("Ready...");
            channel.basicConsume(topic, true, consumer);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

}
