package mountainshop.topic;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

public class SupplierTopicListener extends Thread {
    private final String exchangeName;
    private final String topic;
    private final TopicWriter topicWriter;
    private final String supplierName;
    private final String queueName;
    private int orderCounter = 1;

    public SupplierTopicListener(String exchangeName, TopicWriter topicWriter, String supplierName, String queueName) {
        this.exchangeName = exchangeName;
        this.topic = "order.*." + queueName;
        this.topicWriter = topicWriter;
        this.supplierName = supplierName;
        this.queueName = queueName;
    }

    @Override
    public void run() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);
            channel.queueBind(queueName, exchangeName, topic);

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                    String message = new String(body, StandardCharsets.UTF_8);
                    System.out.println("Otrzymano: " + message);
                    String orderName = message + "." + supplierName + "." + orderCounter;
                    orderCounter += 1;
                    String resultMessage = "Zamowienie " + orderName + " zrealizowane";
                    topicWriter.send(resultMessage, orderName);
                }
            };
            channel.basicConsume(queueName, true, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
