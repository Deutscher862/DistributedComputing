package mountainshop.team;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

class Team {
    static final String TLEN = "tlen";
    static final String BUTY = "plecak";
    static final String PLECAK = "buty";

    public static void main(String[] argv) throws Exception {
        String EXCHANGE_NAME = "mountainShop";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your name: ");
        String name = br.readLine();

        Map<String, QueueWriter> ordersMap = Map.of(
                TLEN, new QueueWriter(TLEN),
                BUTY, new QueueWriter(BUTY),
                PLECAK, new QueueWriter(PLECAK)
        );

        while (true) {
            System.out.print("Pick your order: ");
            String message = br.readLine();

            if (message.equals("quit")) {
                break;
            }
            QueueWriter currentWriter = ordersMap.get(message);
            if (currentWriter != null) {
                currentWriter.send(name + ": " + message);
            } else {
                System.out.println("Item not found");
            }
        }
    }
}
