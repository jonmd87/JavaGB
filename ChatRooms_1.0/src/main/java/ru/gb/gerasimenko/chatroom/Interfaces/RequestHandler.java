package ru.gb.gerasimenko.chatroom.Interfaces;

import ru.gb.gerasimenko.chatroom.server.ChatServer;

public interface RequestHandler {
    public String handler(String data, ChatServer server);
}
