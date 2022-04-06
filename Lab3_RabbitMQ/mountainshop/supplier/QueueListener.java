package mountainshop.supplier;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

class QueueListener extends Thread {
    private final String key;
    private final String supplierName;
    private final TopicWriter topicWriter;

    public QueueListener(String key, String supplierName, TopicWriter topicWriter) {
        this.key = key;
        this.supplierName = supplierName;
        this.topicWriter = topicWriter;
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
                    try {
                        String message = new String(body, StandardCharsets.UTF_8);
                        System.out.println("Otrzymano: " + message);
                        channel.basicAck(envelope.getDeliveryTag(), false);
                        String orderName = message + "." + supplierName;
                        String returnMessage = "Zamowienie " + orderName + " zrealizowane";
                        topicWriter.send(returnMessage, orderName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            channel.basicQos(1);
            channel.basicConsume(key, false, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
