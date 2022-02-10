package ru.gb.gerasimenko.chatroom.Handlers;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler;
import ru.gb.gerasimenko.chatroom.client.ChatClient;
import ru.gb.gerasimenko.chatroom.server.ChatServer;
import ru.gb.gerasimenko.chatroom.server.ClientHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class TargetedDeliveryHandler implements RequestHandler {

    @Override
    public String handler(String data, ChatServer server) {
        System.out.println(data + "|");
        String[] split = data.split(Commands.STR_SEPARATOR.getStr());
        for (String s: split) {
            System.out.println("[" + s + "]");
        }
        ArrayList<String> list = new ArrayList<>(Arrays.asList(split[0].split("from")));
        for (String s : list) {
            System.out.println(s + "|client");
            ClientHandler client = server.getClients().get(s);
            client.sendMessage(split[1]);
        }
//        ClientHandler client = server.getClients().get(split[0]);
//        client.sendMessage(split[1]);
        return null;
    }
}
