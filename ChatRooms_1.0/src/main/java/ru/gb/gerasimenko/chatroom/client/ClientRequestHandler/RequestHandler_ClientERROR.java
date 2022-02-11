package ru.gb.gerasimenko.chatroom.client.ClientRequestHandler;

import ru.gb.gerasimenko.chatroom.Helper.Phrases;
import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler_Client;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

public class RequestHandler_ClientERROR implements RequestHandler_Client {
    @Override
    public String handler(String data, ChatClient client) {
//        System.out.println("ERRORdata" + data);
        byte lang = client.getButtonsController().getLang();
        int ind = Integer.parseInt(data);
        String message = Phrases.values()[ind].value(lang);
        client.getButtonsController().addMessage(message);
        return null;
    }
}
