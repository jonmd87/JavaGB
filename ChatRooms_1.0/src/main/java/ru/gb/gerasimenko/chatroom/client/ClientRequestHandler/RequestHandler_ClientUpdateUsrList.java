package ru.gb.gerasimenko.chatroom.client.ClientRequestHandler;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler_Client;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

import java.util.ArrayList;
import java.util.Arrays;

public class RequestHandler_ClientUpdateUsrList implements RequestHandler_Client {
    @Override
    public String handler(String data, ChatClient client) {
        System.out.println("in UpdateUserlistDATA-->|" + data + "|");
        String[] split = this.dataToStringArray(data, Commands.STR_SEPARATOR.getStr());
        ArrayList<String> list = new ArrayList<>(Arrays.asList(split));
        client.getButtonsController().updateMemberList(list);
        return null;
    }
}
