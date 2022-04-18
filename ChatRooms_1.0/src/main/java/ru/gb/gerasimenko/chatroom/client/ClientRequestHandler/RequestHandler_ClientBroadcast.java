package ru.gb.gerasimenko.chatroom.client.ClientRequestHandler;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler_Client;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

public class RequestHandler_ClientBroadcast implements RequestHandler_Client {
    @Override
    public void handler(String data, ChatClient client) {
        if (!data.contains(Commands.EMPTY.getStr())) {
            client.getButtonsController().addMessage(data);
        }
    }
}
