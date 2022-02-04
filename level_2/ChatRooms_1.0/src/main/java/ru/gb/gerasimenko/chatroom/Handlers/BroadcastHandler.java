package ru.gb.gerasimenko.chatroom.Handlers;

import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler;
import ru.gb.gerasimenko.chatroom.server.ChatServer;
import ru.gb.gerasimenko.chatroom.server.ClientHandler;

public class BroadcastHandler implements RequestHandler {

    @Override
    public String handler(String data, ChatServer server) {
        System.out.println(data + "|brdct");
        for (ClientHandler client : server.getClients().values()) {
            client.sendMessage(data);
        }
        return null;
    }
}
