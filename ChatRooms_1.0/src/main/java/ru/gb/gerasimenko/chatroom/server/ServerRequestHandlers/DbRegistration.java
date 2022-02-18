package ru.gb.gerasimenko.chatroom.server.ServerRequestHandlers;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Interfaces.ServerRequestHandler;
import ru.gb.gerasimenko.chatroom.server.ChatServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbRegistration implements ServerRequestHandler {
    @Override
    public String handler(String data, ChatServer server) {
        System.out.println("IN DBregistration[" + data + "]");
        String[] split = data.split(Commands.ARG_SEPARATOR.getStr());
        for (String s : split) {
            System.out.printf("[%s]\n", s);
        }
        addNewUserToDataBase(server.getDataBase().getConnection(), split);
        return null;
    }

    private void addNewUserToDataBase(Connection connection, String[] split) {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into users (nick, login, password) values (?,?,?)")) {
            ps.setString(1, split[0]);
            ps.setString(2, split[1]);
            ps.setInt(3, Integer.parseInt(split[3]));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
