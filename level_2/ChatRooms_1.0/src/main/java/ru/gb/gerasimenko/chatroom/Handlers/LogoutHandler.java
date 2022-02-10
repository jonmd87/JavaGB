package ru.gb.gerasimenko.chatroom.Handlers;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler;
import ru.gb.gerasimenko.chatroom.client.ChatClient;
import ru.gb.gerasimenko.chatroom.server.ChatServer;
import ru.gb.gerasimenko.chatroom.server.ClientHandler;

public class LogoutHandler implements RequestHandler {

    @Override
    public String handler(String data, ChatServer server) {
        System.out.println("data[" + data +"]");
        String split = data.split(Commands.STR_SEPARATOR.getStr())[0];
        ClientHandler client = server.getClients().get(split);
        server.unsubscribe(client);
        String request = Commands.BROADCAST.getStr() + Commands.CMD_SEPARATOR.getStr() +
                Commands.MEMBER_LEFT.getStr() + Commands.STR_SEPARATOR.getStr() + split;
        System.out.println(request);
        server.distribution(request);
        return null;
    }
}
