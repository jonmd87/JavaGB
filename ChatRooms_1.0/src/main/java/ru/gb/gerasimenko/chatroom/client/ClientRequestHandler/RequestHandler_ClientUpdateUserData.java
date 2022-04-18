package ru.gb.gerasimenko.chatroom.client.ClientRequestHandler;

import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler_Client;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

public class RequestHandler_ClientUpdateUserData implements RequestHandler_Client {
    @Override
    public void handler(String data, ChatClient client) {
        client.setNick(data);
    }

    @Override
    public String[] dataToStringArray(String data, String separator) {
        return RequestHandler_Client.super.dataToStringArray(data, separator);
    }
}
