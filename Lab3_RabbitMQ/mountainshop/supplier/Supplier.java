package mountainshop.supplier;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Supplier {
    public static void main(String[] argv) throws Exception {
        String EXCHANGE_NAME = "mountainShop";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter connection key: ");
        String key = br.readLine();

        QueueListener listener = new QueueListener(key, EXCHANGE_NAME);
        listener.start();
    }
}
