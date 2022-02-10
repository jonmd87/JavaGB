package ru.gb.gerasimenko.chatroom.server.ServerRequestHandlers;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Interfaces.ServerRequestHandler;
import ru.gb.gerasimenko.chatroom.server.ChatServer;
import ru.gb.gerasimenko.chatroom.server.ClientHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class TargetedDeliveryHandlerServer implements ServerRequestHandler {

    @Override
    public String handler(String data, ChatServer server) {
        System.out.println(data + "|");
        return null;
    }
}
