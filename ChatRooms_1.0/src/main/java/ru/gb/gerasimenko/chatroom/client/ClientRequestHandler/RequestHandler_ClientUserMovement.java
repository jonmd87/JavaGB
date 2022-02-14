package ru.gb.gerasimenko.chatroom.client.ClientRequestHandler;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;
import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler_Client;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

public class RequestHandler_ClientUserMovement implements RequestHandler_Client {
    @Override
    public void handler(String data, ChatClient client) {
        System.out.println("USERMOVE |" + data + "|");
        String[] split = dataToStringArray(data, Commands.STR_SEPARATOR.getStr());
        String message;
        int index = Integer.parseInt(split[1]);
        message = split[0] + Phrases.values()[index].value(client.getButtonsController().getLang());
        System.out.println("message =" + message);
        client.getButtonsController().addMessage(message);
    }
}
