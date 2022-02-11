package ru.gb.gerasimenko.chatroom.server.ServerRequestHandlers;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;
import ru.gb.gerasimenko.chatroom.Interfaces.ServerRequestHandler;
import ru.gb.gerasimenko.chatroom.server.ChatServer;
import ru.gb.gerasimenko.chatroom.server.ClientHandler;

import java.io.IOException;

public class LogoutHandlerServer implements ServerRequestHandler {
    @Override
    public String handler(String data, ChatServer server) {
        try {
            System.out.println("in logoutHandler |" + data + "|");
            ClientHandler client = server.getClients().get(data);
            client.sendMessage(Commands.LOGOUT.getStr() + Commands.ARG_SEPARATOR.getStr() + StrConsts.END_LINE.getStr());
            client.getParticipant().close();
            server.unsubscribe(data);
            server.sendUserList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
