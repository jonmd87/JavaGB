package ru.gb.gerasimenko;

import ru.gb.gerasimenko.Helper.Define;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoServer {

    private byte lang = 1;
    private Participant server;

    public static void main(String[] args) {
        new EchoServer();
    }

    public EchoServer() {
        setServer();
        System.out.printf(Define.SESSION_CLOSED.getValue(lang));
    }

    private void setServer() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println(Define.SERV_RUNNING.getValue(lang));
            System.out.println(Define.SERV_WAITNG.getValue(lang));
            server = new Participant(serverSocket);
            if (server.connectionActive()) {
                System.out.println(Define.CLIENT_CONNNECTED.getValue(lang));
                server.startCommandListener();
            }
            while (true) {
                if (server.connectionActive()) {
                    final String incMsg = server.readMessage();
                    if (Define.END_SESION.getValue(lang).equalsIgnoreCase(incMsg)) {
                        server.sendMessage(incMsg);
                        server.close();
                        break;
                    }
                    printIncomingMessage(incMsg);
                    server.sendMessage(processing(incMsg));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void printIncomingMessage(String incMsg) {
        System.out.printf("%s[%s]\n", Define.CLIENT.getValue(lang), incMsg);
    }

    private String processing(String incMsg) {
        String temp = "";

        if (incMsg != null && incMsg.length() > 0) {
            temp = incMsg.toUpperCase();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
