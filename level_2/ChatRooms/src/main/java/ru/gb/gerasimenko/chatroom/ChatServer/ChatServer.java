package ru.gb.gerasimenko.chatroom.ChatServer;

import ru.gb.gerasimenko.chatroom.Helper.Defines;
import ru.gb.gerasimenko.chatroom.Participant;

import java.io.IOException;
import java.net.ServerSocket;

public class ChatServer {

    private Participant server;

    public ChatServer() {
    }

    public void setServer() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println(Defines.SERV_RUNNING.getStr());
            System.out.println(Defines.SERV_WAITNG.getStr());
            server = new Participant(serverSocket);
            if (server.connectionActive()) {
                System.out.println(Defines.CLIENT_CONNNECTED.getStr());
                server.startCommandListener();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } ;
    }
}
