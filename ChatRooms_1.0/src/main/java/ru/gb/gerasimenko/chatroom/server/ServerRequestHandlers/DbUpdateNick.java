package ru.gb.gerasimenko.chatroom.server.ServerRequestHandlers;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;
import ru.gb.gerasimenko.chatroom.Interfaces.ServerRequestHandler;
import ru.gb.gerasimenko.chatroom.server.ChatServer;
import ru.gb.gerasimenko.chatroom.server.ClientHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class DbUpdateNick implements ServerRequestHandler {
    @Override
    public String handler(String data, ChatServer server) {
//        incoming data [newNick]+[oldNick]+[Login]+[Password]
        System.out.println("IN UPDATE |" + data + "|");
        String[] split = data.split(Commands.ARG_SEPARATOR.getStr());
        for (String s : split) {
            System.out.printf("[%s]\n", s);
        }
        final String newNick = split[0];
        final String oldNick = split[1];
        final String login = split[2];
        final Integer password = Integer.parseInt(split[3]);
        if (server.getClients().containsKey(oldNick) && !server.getClients().containsKey(newNick)) {
            updateNickInDB(server.getDataBase().getConnection(), newNick, login, password);
            updateNickInClientsList(server, oldNick, newNick);
            server.distribution(Commands.BROADCAST.getStr() +
                                    Commands.CMD_SEPARATOR.getStr() +
                                        Commands.NOTIFICATION.getStr() +
                                            Phrases.CHANGED_NICK.ordinal() +
                                                Commands.STR_SEPARATOR.getStr() +
                                                    oldNick + " --> " + newNick);
        }
        return null;
    }

    private void updateNickInClientsList(ChatServer server, String oldNick, String newNick) {
        HashMap<String, ClientHandler> clients = server.getClients(); //извлекаем список клиентов
        ClientHandler temp = clients.get(oldNick); //у старого ника извлекаем clientHandler
        clients.put(newNick, temp); // вставляем в список клиентов новый ник и clientHandler
        clients.get(newNick).setNick(newNick); // заменяем ник в clientHandler
        clients.remove(oldNick); // удаляем старый ник и clientHandler из списка
        server.sendUserList(); // рассылаем новый список подключенных клиентов
    }

    private void updateNickInDB(Connection connection, String newNick, String login, Integer password) {
        try (PreparedStatement ps = connection.prepareStatement("" +
                "udate users set nick = ? where login = ? password = ?")) {
                ps.setString(1, newNick);
                ps.setString(2, login);
                ps.setInt(3, password);
                ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
