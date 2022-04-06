package mountainshop.supplier;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Supplier {
    public static void main(String[] argv) throws Exception {
        String EXCHANGE_NAME = "mountainShop";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter supplier name: ");
        String name = br.readLine();

        System.out.println("Enter first product: ");
        String firstProduct = br.readLine();

        System.out.println("Enter second product: ");
        String secondProduct = br.readLine();

        new QueueListener(firstProduct).start();
        new QueueListener(secondProduct).start();
    }
}
