package mountainshop.users;

import mountainshop.topic.TopicWriter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Administrator {
    static final String EXCHANGE_NAME = "systemInfo";
    static final String MESSAGE_FOR_ALL = "Wiadomosc do wszystkich";
    static final String MESSAGE_FOR_TEAMS = "Wiadomosc do ekip";
    static final String MESSAGE_FOR_SUPPLIER = "Wiadomosc do dostawcow";
    static final String TEAM_TOPIC = "log.team";
    static final String SUPPLIER_TOPIC = "log.supplier";

    public static void main(String[] argv) throws Exception {
        TopicWriter topicWriter = new TopicWriter(EXCHANGE_NAME);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Wybierz grupe uzytkownikow: ");
            String userGroup = br.readLine();

            if (userGroup.equals("q"))
                break;

            switch (userGroup) {
                case "a" -> {
                    topicWriter.send(MESSAGE_FOR_ALL, TEAM_TOPIC);
                    topicWriter.send(MESSAGE_FOR_ALL, SUPPLIER_TOPIC);
                }
                case "t" -> topicWriter.send(MESSAGE_FOR_TEAMS, TEAM_TOPIC);
                case "s" -> topicWriter.send(MESSAGE_FOR_SUPPLIER, SUPPLIER_TOPIC);
            }
        }
    }
}
