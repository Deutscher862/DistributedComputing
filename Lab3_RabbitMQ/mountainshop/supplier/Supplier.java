package mountainshop.supplier;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Supplier {
    final static String EXCHANGE_NAME = "confirmOrder";

    public static void main(String[] argv) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Wprowadz imie: ");
        String name = br.readLine();

        System.out.println("Wprowadz pierwszy produkt: ");
        String firstProduct = br.readLine();

        System.out.println("Wprowadz drugi produkt: ");
        String secondProduct = br.readLine();

        TopicWriter topicWriter = new TopicWriter(EXCHANGE_NAME);

        new QueueListener(firstProduct, name, topicWriter).start();
        new QueueListener(secondProduct, name, topicWriter).start();
    }
}
