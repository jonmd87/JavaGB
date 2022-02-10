package ru.gb.gerasimenko.chatroom.client.ClientRequestHandler;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler_Client;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

import java.io.IOException;

public class RequestHandler_ClientLogout implements RequestHandler_Client {
    @Override
    public String handler(String data, ChatClient client) {
        System.out.println("in Logout");
        try {
            client.getParticipant().close();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("in RequestHandler_ClientLogout");
            e.printStackTrace();
        }
        System.out.println(client.getParticipant().connectionActive());
        return null;
    }
}
