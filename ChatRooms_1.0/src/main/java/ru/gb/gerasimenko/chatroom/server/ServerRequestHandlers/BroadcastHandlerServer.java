package ru.gb.gerasimenko.chatroom.server.ServerRequestHandlers;
import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Interfaces.ServerRequestHandler;
import ru.gb.gerasimenko.chatroom.server.ChatServer;
import ru.gb.gerasimenko.chatroom.server.ClientHandler;

public class BroadcastHandlerServer implements ServerRequestHandler {

    @Override
    public String handler(String data, ChatServer server) {
        System.out.println("BRDCST [" + data + "]");
        for (ClientHandler client : server.getClients().values()) {
            if (!data.contains(Commands.TECHNICAL_MSG.getStr())) {
                client.getHistoryControl().writeInHistoryFile(data.split(Commands.ARG_SEPARATOR.getStr())[1]);
            }
            client.sendMessage(data);
        }
        return null;
    }
}
