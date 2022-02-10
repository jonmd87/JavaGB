package ru.gb.gerasimenko.chatroom.server;

import ru.gb.gerasimenko.chatroom.Handlers.Handler;
import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.DgtlConsts;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;
import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

public class ChatServer {
    private final HashMap<String, ClientHandler> clients;
    private final Handler handler;

    public ChatServer() {
        clients = new HashMap<>();
        handler = new Handler();
    }

    public void start() {
        System.out.println(StrConsts.SERV_WAITNG.getStr());
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(DgtlConsts.PORT.value())) {
                new ClientHandler(this, serverSocket);
                System.out.println(StrConsts.WAITIN_NEXT_CONNECTION.getStr());
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }
        }
    }

    public String distribution(String message) {
        String[] split = message.split(Commands.CMD_SEPARATOR.getStr());
        final RequestHandler requestHandler = handler.getHandler().get(split[0]);
        System.out.println("incoming " + message);
//        for (int i = 0; i < split.length; i++) {
//            System.out.printf("[%s]\n", split[i]);
//        }
        System.out.println("distribution throw [" + split[1]+ "]");
        System.out.println("-----------------");
        return requestHandler.handler(split[1], this);
    }

    public boolean subscribe(ClientHandler newClient) {
        if (clients.containsKey(newClient.getNick())) {
            return false;
        }
        clients.put(newClient.getNick(), newClient);
        sendUserList();
        return true;
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client.getNick());
        sendUserList();
    }

    private void sendUserList() {
        String data = Commands.BROADCAST.getStr() + Commands.CMD_SEPARATOR.getStr() +
                Commands.UPDATE_USERLIST.getStr() + Commands.STR_SEPARATOR.getStr();
        for (String s : clients.keySet()) {
            data += s + Commands.STR_SEPARATOR.getStr();
        }
        distribution(data);
    }

    public HashMap<String, ClientHandler> getClients() {
        return clients;
    }
}
