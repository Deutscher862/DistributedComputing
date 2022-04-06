package mountainshop.users;

import mountainshop.queue.QueueWriter;
import mountainshop.topic.TopicListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

class Team {
    static final String TLEN = "tlen";
    static final String BUTY = "plecak";
    static final String PLECAK = "buty";
    static final String EXCHANGE_NAME = "confirmOrder";
    final static String ADMIN_EXCHANGE = "systemInfo";
    static String name;
    static Map<String, QueueWriter> ordersMap;

    public static void main(String[] argv) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Wprowadz imie: ");
        name = br.readLine();

        ordersMap = Map.of(
                TLEN, new QueueWriter(TLEN),
                BUTY, new QueueWriter(BUTY),
                PLECAK, new QueueWriter(PLECAK)
        );

        runTopicListeners();

        while (true) {
            System.out.println("Wybierz zamowienie: ");
            String message = br.readLine();

            if (message.equals("q")) {
                break;
            } else if (message.equals("seq")) {
                choseWriterAndSend(TLEN);
                choseWriterAndSend(TLEN);
                choseWriterAndSend(BUTY);
                choseWriterAndSend(BUTY);
                choseWriterAndSend(PLECAK);
                choseWriterAndSend(PLECAK);
            } else {
                choseWriterAndSend(message);
            }
        }
    }

    public static void choseWriterAndSend(String message) {
        QueueWriter currentWriter = ordersMap.get(message);
        if (currentWriter != null) {
            try {
                currentWriter.send("order." + name + "."  + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Produkt nie zostal znaleziony");
        }
    }

    public static void runTopicListeners() {
        String key = "order." + name + ".#";
        new TopicListener(EXCHANGE_NAME, key).start();
        new TopicListener(ADMIN_EXCHANGE, "*.team").start();
    }
}
