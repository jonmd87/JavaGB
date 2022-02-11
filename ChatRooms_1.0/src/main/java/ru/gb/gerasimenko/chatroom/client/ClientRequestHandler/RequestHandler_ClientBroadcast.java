package ru.gb.gerasimenko.chatroom.client.ClientRequestHandler;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;
import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler_Client;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

public class RequestHandler_ClientBroadcast implements RequestHandler_Client {
    @Override
    public String handler(String data, ChatClient client) {
        System.out.println("in broadcat " + data + "|");
        data = makeNotice(data, client.getButtonsController().getLang());
        System.out.println("data =" + data + "|");
        client.getButtonsController().addMessage(data);
        return null;
    }

    private String makeNotice(String data, byte lang) {
        if (data.startsWith(Commands.USER_MOVEMENTS.getStr())) {
            String[] split = dataToStringArray(data, Commands.STR_SEPARATOR.getStr());
            String message;
            int index = Integer.parseInt(split[2]);
            message = split[1] + Phrases.values()[index].value(lang);
            System.out.println("message =" + message);
            data = message;
        }
        return data;
    }
}
