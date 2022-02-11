package ru.gb.gerasimenko.chatroom.server.ServerRequestHandlers;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Interfaces.ServerRequestHandler;
import ru.gb.gerasimenko.chatroom.server.ChatServer;

public class TargetedDeliveryHandlerServer implements ServerRequestHandler {
    @Override
    public String handler(String data, ChatServer server) {
        System.out.println("TARGET |" + data + "|");
        String[] split = data.split(Commands.ARG_SEPARATOR.getStr());
        for (String s : split[1].split(Commands.STR_SEPARATOR.getStr())) {
            System.out.println(server.getClients().get(s).getNick());
            server.getClients().get(s).sendMessage(Commands.BROADCAST.getStr()
                                    + Commands.ARG_SEPARATOR.getStr() + split[0]);
        }
        return null;
    }
}
