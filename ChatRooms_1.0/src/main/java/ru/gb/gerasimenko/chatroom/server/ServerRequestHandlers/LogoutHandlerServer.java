package ru.gb.gerasimenko.chatroom.server.ServerRequestHandlers;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;
import ru.gb.gerasimenko.chatroom.Interfaces.ServerRequestHandler;
import ru.gb.gerasimenko.chatroom.server.ChatServer;

public class LogoutHandlerServer implements ServerRequestHandler {
    @Override
    public String handler(String data, ChatServer server) {
        System.out.println("in logoutHandler |" + data + "|");
        server.getClients().get(data).sendMessage(Commands.LOGOUT.getStr() + Commands.ARG_SEPARATOR.getStr() +
                StrConsts.END_LINE.getStr());
        server.unsubscribe(data);
        server.sendUserList();
        System.out.println(Commands.LOGOUT.getStr() + Commands.ARG_SEPARATOR);
        return null;
    }
}
