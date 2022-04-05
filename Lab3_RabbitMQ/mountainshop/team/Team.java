package mountainshop.team;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

class Team {
    public static void main(String[] argv) throws Exception {
        // exchange
        String EXCHANGE_NAME = "mountainShop";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your name: ");
        String name = br.readLine();

        Map<String, QueueWriter> ordersMap = Map.of(
                "tlen", new QueueWriter(EXCHANGE_NAME, "tlen"),
                "buty", new QueueWriter(EXCHANGE_NAME, "buty"),
                "plecak", new QueueWriter(EXCHANGE_NAME, "plecak")
        );

        while (true) {
            System.out.print("Pick your order: ");
            String message = br.readLine();

            if (message.equals("quit")) {
                break;
            }
            QueueWriter currentWriter = ordersMap.get(message);
            if (currentWriter != null) {
                currentWriter.send(name + " " + message);
            } else {
                System.out.println("Item not found");
            }


        }
    }
}
