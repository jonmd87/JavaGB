package ru.gb.gerasimenko;

import ru.gb.gerasimenko.Helper.Define;
import java.io.IOException;

public class EchoClient {
    private byte lang = 1;
    private Participant client;

    public static void main(String[] args) {
        try {
            new EchoClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public EchoClient() throws IOException {
        setClient();
        System.out.printf(Define.SESSION_CLOSED.getValue(lang));
    }

    public  void setClient() throws IOException {
            client = new Participant("localhost", 8080);
            if (client.connectionActive()) {
                System.out.println(Define.SERVER_CONNNECTED.getValue(lang));
                client.startCommandListener();
            }
            listenNet();
    }

    public void listenNet() throws IOException {
        while (true) {
            final String incMsg = client.readMessage();
            if (Define.END_SESION.getValue(lang).equalsIgnoreCase(incMsg)) {
                client.sendMessage(incMsg);
                client.close();
                break ;
            }
            printIncomingMessage(incMsg);
        }
    }

    private  void printIncomingMessage(String incMsg) {
        System.out.printf("%s[%s]\n", Define.SERVER.getValue(lang), incMsg);
    }
}
