package mountainshop.team;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Team {
    public static void main(String[] argv) throws Exception {
        // exchange
        String EXCHANGE_NAME = "mountainShop";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter connection key: ");
        String key = br.readLine();

        QueueWriter writer = new QueueWriter(EXCHANGE_NAME, key);
        writer.establishConnection();

        while (true) {

            // read msg
            System.out.print("Enter message: ");
            String message = br.readLine();

            // break condition
            if ("exit".equals(message)) {
                break;
            }

            // publish
            writer.send(message);
        }
    }
}
