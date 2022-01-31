package ru.gb.gerasimenko.chatroom.ChatServer;

import ru.gb.gerasimenko.chatroom.Participant;

import java.io.IOException;
import java.net.ServerSocket;

public class ChatServer {

    private Participant server;

    public ChatServer() {
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            server = new Participant(serverSocket);
            if (server.connectionActive()) {
                server.startCommandListener();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } ;
    }
}
