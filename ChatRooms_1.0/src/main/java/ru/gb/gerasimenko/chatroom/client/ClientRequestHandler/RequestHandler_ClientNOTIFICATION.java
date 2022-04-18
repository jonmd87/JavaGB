package ru.gb.gerasimenko.chatroom.client.ClientRequestHandler;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;
import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler_Client;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

public class RequestHandler_ClientNOTIFICATION implements RequestHandler_Client {
    @Override
    public void handler(String data, ChatClient client) {
        String[] split = data.split(Commands.STR_SEPARATOR.getStr());
        byte lang = client.getButtonsController().getLang();
        int ind = Integer.parseInt(split[0]);
        String message = Phrases.values()[ind].value(lang) + split[1];
        client.getButtonsController().addMessage(message);
    }
}
