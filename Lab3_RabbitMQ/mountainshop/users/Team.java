package mountainshop.users;

import mountainshop.topic.TopicListener;
import mountainshop.topic.TopicWriter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Team {
    static final String TLEN = "tlen";
    static final String BUTY = "plecak";
    static final String PLECAK = "buty";
    static final String CONFIRM_ORDER_EXCHANGE = "confirmOrder";
    final static String ADMIN_EXCHANGE = "systemInfo";
    final static String ORDER_EHCHANGE = "mountainShop";
    static TopicWriter topicWriter;
    static String name;

    public static void main(String[] argv) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Wprowadz imie: ");
        name = br.readLine();

        topicWriter = new TopicWriter(ORDER_EHCHANGE);
        runTopicListeners();

        while (true) {
            System.out.println("Wybierz zamowienie: ");
            String product = br.readLine();

            if (product.equals("q")) {
                break;
            } else if (product.equals("seq")) {
                sendSequence();
            } else {
                String message = "order." + name + "." + product;
                topicWriter.send(message, message);
            }
        }
    }

    public static void runTopicListeners() {
        String key = "order." + name + ".#";
        new TopicListener(CONFIRM_ORDER_EXCHANGE, key).start();
        new TopicListener(ADMIN_EXCHANGE, "*.team").start();
    }

    public static void sendSequence() {
        String tMessage = "order." + name + "." + TLEN;
        String bMessage = "order." + name + "." + BUTY;
        String pMessage = "order." + name + "." + PLECAK;
        topicWriter.send(tMessage, tMessage);
        topicWriter.send(tMessage, tMessage);
        topicWriter.send(bMessage, bMessage);
        topicWriter.send(bMessage, bMessage);
        topicWriter.send(pMessage, pMessage);
        topicWriter.send(pMessage, pMessage);
    }
}
