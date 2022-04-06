package mountainshop.team;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

class TopicListener extends Thread {
    private final String exchangeName;
    private final String topic;

    public TopicListener(String exchangeName, String topic) {
        this.exchangeName = exchangeName;
        this.topic = topic;
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
                    System.out.println("Otrzymano: " + message);
                }
            };
            channel.basicConsume(queueName, true, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
