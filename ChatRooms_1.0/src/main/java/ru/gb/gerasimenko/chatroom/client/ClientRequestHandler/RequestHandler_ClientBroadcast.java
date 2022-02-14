package ru.gb.gerasimenko.chatroom.client.ClientRequestHandler;

import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler_Client;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

public class RequestHandler_ClientBroadcast implements RequestHandler_Client {
    @Override
    public void handler(String data, ChatClient client) {
        System.out.println("in broadcat " + data + "|");
        System.out.println("data =" + data + "|");
        client.getButtonsController().addMessage(data);
    }
}
