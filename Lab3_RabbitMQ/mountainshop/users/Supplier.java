package mountainshop.users;

import mountainshop.queue.QueueListener;
import mountainshop.topic.TopicListener;
import mountainshop.topic.TopicWriter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Supplier {
    final static String ORDER_EXCHANGE = "confirmOrder";
    final static String ADMIN_EXCHANGE = "systemInfo";

    public static void main(String[] argv) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Wprowadz imie: ");
        String name = br.readLine();

        System.out.println("Wprowadz pierwszy produkt: ");
        String firstProduct = br.readLine();

        System.out.println("Wprowadz drugi produkt: ");
        String secondProduct = br.readLine();

        TopicWriter topicWriter = new TopicWriter(ORDER_EXCHANGE);

        new QueueListener(firstProduct, name, topicWriter).start();
        new QueueListener(secondProduct, name, topicWriter).start();
        new TopicListener(ADMIN_EXCHANGE, "*.supplier").start();
    }
}
