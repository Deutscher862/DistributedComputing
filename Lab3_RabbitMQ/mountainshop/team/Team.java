package mountainshop.team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

class Team {
    static final String TLEN = "tlen";
    static final String BUTY = "plecak";
    static final String PLECAK = "buty";
    static final String EXCHANGE_NAME = "confirmOrder";
    static String name;
    static Map<String, QueueWriter> ordersMap;
    static int orderId = 1;

    public static void main(String[] argv) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Wprowadz imie: ");
        name = br.readLine();

        ordersMap = Map.of(
                TLEN, new QueueWriter(TLEN),
                BUTY, new QueueWriter(BUTY),
                PLECAK, new QueueWriter(PLECAK)
        );

        runTopicListener();

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
                currentWriter.send(name + ".order" + orderId + "." + message);
                orderId += 1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Produkt nie znaleziony");
        }
    }

    public static void runTopicListener() {
        String key = name + ".*.*.*";
        new TopicListener(EXCHANGE_NAME, key).start();
    }
}
