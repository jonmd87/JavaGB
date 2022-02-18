package ru.gb.gerasimenko.chatroom.server.ServerRequestHandlers;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Interfaces.ServerRequestHandler;
import ru.gb.gerasimenko.chatroom.server.ChatServer;
import ru.gb.gerasimenko.chatroom.server.DataBaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthenticationHandlerServer implements ServerRequestHandler {
    @Override
    public String handler(String data, ChatServer server) {
        System.out.println("AUTH |" + data +"|");
        String[] split = data.split(Commands.ARG_SEPARATOR.getStr());
        String login = split[0];
        Integer password = Integer.parseInt(split[1]);
        return getNickByLoginPassword(server.getDataBase(), login, password);
    }

    public String getNickByLoginPassword(DataBaseConnection db,  String login, Integer password) {
        try (PreparedStatement ps = db.getConnection().prepareStatement(
                "select * from user where login = ? and password = ?")) {
            ps.setString(1, login);
            ps.setInt(2, password);
            final ResultSet resultSet = ps.executeQuery();
            return (resultSet.next()) ? resultSet.getString(2) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
