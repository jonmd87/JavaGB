package ru.gb.gerasimenko.chatroom.server;

import ru.gb.gerasimenko.chatroom.Helper.Phrases;
import ru.gb.gerasimenko.chatroom.server.ServerRequestHandlers.*;
import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.DgtlConsts;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;
import ru.gb.gerasimenko.chatroom.Interfaces.ServerRequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class ChatServer {
    private final HashMap<String, ClientHandler> clients;
    private final DataBaseConnection dataBase = new DataBaseConnection();
    private final Map<String, ServerRequestHandler> handler;

    public ChatServer() {
        clients = new HashMap<>();
        handler = new HashMap<>();
        initHandlers();
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
        final ServerRequestHandler tempHandler = handler.get(split[0]);
        System.out.println("incoming " + message);
        System.out.println("distribution throw [" + split[1] + "]");
        return tempHandler.handler(split[1], this);
    }

    public boolean subscribe(ClientHandler newClient) {
        String nick = newClient.getNick();
        if (nick == null || clients.containsKey(nick)) {
            return false;
        }
        clients.put(nick, newClient);
        String message = Commands.BROADCAST.getStr() +
                Commands.CMD_SEPARATOR.getStr() +
                Commands.USER_MOVEMENTS.getStr() +
                Commands.ARG_SEPARATOR.getStr() +
                nick + Commands.STR_SEPARATOR.getStr() +
                Phrases.ENTERED_IN_CHAT.ordinal();
        this.distribution(message);
        return true;
    }

    public void unsubscribe(String nick) {
        clients.remove(nick);
        if (clients.size() > 0) {
            String message = Commands.BROADCAST.getStr() +
                    Commands.CMD_SEPARATOR.getStr() +
                    Commands.USER_MOVEMENTS.getStr() +
                    Commands.ARG_SEPARATOR.getStr() +
                    nick + Commands.STR_SEPARATOR.getStr() +
                    Phrases.LEAVE_CHAT.ordinal();
            this.distribution(message);
        }
    }

    public void sendUserList() {
        if (clients.size() > 0) {
            System.out.println(clients.size());
            String data = Commands.BROADCAST.getStr() +
                    Commands.CMD_SEPARATOR.getStr() +
                    Commands.UPDATE_USERS_LIST.getStr() +
                    Commands.ARG_SEPARATOR.getStr();
            for (String s : clients.keySet()) {
                data += s + Commands.STR_SEPARATOR.getStr();
            }
            distribution(data);
        }
    }

    private void initHandlers() {
        handler.put(Commands.AUTH_IN.getStr(), new AuthenticationHandlerServer());
        handler.put(Commands.BROADCAST.getStr(), new BroadcastHandlerServer());
        handler.put(Commands.DB_REGISTER.getStr(), new DbRegistration());
        handler.put(Commands.DB_UPDATE_NICK.getStr(), new DbUpdateNick());
        handler.put(Commands.LOGOUT.getStr(), new LogoutHandlerServer());
        handler.put(Commands.TARGET_DELIVERY.getStr(), new TargetedDeliveryHandlerServer());
    }

    public HashMap<String, ClientHandler> getClients() {
        return clients;
    }

    public Map<String, ServerRequestHandler> getHandler() {
        return handler;
    }

    public DataBaseConnection getDataBase() {
        return dataBase;
    }
}
