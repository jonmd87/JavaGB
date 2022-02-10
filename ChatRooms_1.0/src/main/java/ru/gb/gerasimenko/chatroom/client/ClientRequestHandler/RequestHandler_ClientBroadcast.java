package ru.gb.gerasimenko.chatroom.client.ClientRequestHandler;

import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler_Client;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

public class RequestHandler_ClientBroadcast implements RequestHandler_Client {
    @Override
    public String handler(String data, ChatClient client) {
        System.out.println("in broadcat " + data + "|");
        client.getButtonsController().addMessage(data);
        return null;
    }
}
