package ru.gb.gerasimenko.chatroom.client.ClientRequestHandler;

import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler_Client;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

import java.io.IOException;

public class RequestHandler_ClientLogout implements RequestHandler_Client {
    @Override
    public void handler(String data, ChatClient client) {
        try {
            client.setNick(null);
            client.getParticipant().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
