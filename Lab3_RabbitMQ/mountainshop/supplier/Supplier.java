package mountainshop.supplier;

class Supplier {
    public static void main(String[] argv) throws Exception {
        String EXCHANGE_NAME = "mountainShop";

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Enter connection key: ");
//        String key = br.readLine();

        new QueueListener("tlen", EXCHANGE_NAME).start();
        new QueueListener("buty", EXCHANGE_NAME).start();
        new QueueListener("plecak", EXCHANGE_NAME).start();
    }
}
