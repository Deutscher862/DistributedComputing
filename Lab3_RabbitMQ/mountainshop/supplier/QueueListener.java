package mountainshop.supplier;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

class QueueListener extends Thread {
    private final String key;

    public QueueListener(String key) {
        this.key = key;
    }

    public void run() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(key, false, false, false, null);

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                    String message = new String(body, StandardCharsets.UTF_8);
                    System.out.println(message);
                }
            };
            channel.basicConsume(key, false, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
