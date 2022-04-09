package mountainshop.users;

import mountainshop.topic.SupplierTopicListener;
import mountainshop.topic.TopicListener;
import mountainshop.topic.TopicWriter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Supplier {
    final static String ORDER_CONFIRM_EXCHANGE = "confirmOrder";
    final static String ADMIN_EXCHANGE = "systemInfo";
    final static String ORDER_EHCHANGE = "mountainShop";


    public static void main(String[] argv) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Wprowadz imie: ");
        String name = br.readLine();

        System.out.println("Wprowadz pierwszy produkt: ");
        String firstProduct = br.readLine();

        System.out.println("Wprowadz drugi produkt: ");
        String secondProduct = br.readLine();

        TopicWriter topicWriter = new TopicWriter(ORDER_CONFIRM_EXCHANGE);

        new SupplierTopicListener(ORDER_EHCHANGE, topicWriter, name, firstProduct).start();
        new SupplierTopicListener(ORDER_EHCHANGE, topicWriter, name, secondProduct).start();
        new TopicListener(ADMIN_EXCHANGE, "*.supplier").start();

        System.out.println("Czekam na wiadomosci...");
    }
}
