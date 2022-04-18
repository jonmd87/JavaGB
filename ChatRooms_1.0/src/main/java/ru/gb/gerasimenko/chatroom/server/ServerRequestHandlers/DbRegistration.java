package ru.gb.gerasimenko.chatroom.server.ServerRequestHandlers;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;
import ru.gb.gerasimenko.chatroom.Interfaces.ServerRequestHandler;
import ru.gb.gerasimenko.chatroom.server.ChatServer;
import ru.gb.gerasimenko.chatroom.server.HistoryControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbRegistration implements ServerRequestHandler {
    @Override
    public String handler(String data, ChatServer server) {
        System.out.println("IN DBregistration[" + data + "]");
        String[] split = data.split(Commands.ARG_SEPARATOR.getStr());
        if (!checkForNickAndLogin(server.getDataBase().getConnection(), split)) {
            addNewUserToDataBase(server.getDataBase().getConnection(), split);
            new HistoryControl(split[0]);
        }
        return Commands.DB_REGISTER.getStr();
    }

    private boolean checkForNickAndLogin(Connection connection, String[] split) {
        boolean answer = true;
        try (PreparedStatement ps = connection.prepareStatement(
                "select * from " + StrConsts.CHATUSERS.getStr() + " where nick = ? and login = ?")){
            ps.setString(1, split[0]);
            ps.setString(1, split[1]);
            ResultSet resultSet = ps.executeQuery();
            answer = resultSet.next(); // если [return resultSet.next();] --> надо обворачивать в try-catch
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    private void addNewUserToDataBase(Connection connection, String[] split) {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into "  + StrConsts.CHATUSERS.getStr() + "(nick, login, password) values (?,?,?)")) {
            ps.setString(1, split[0]);
            ps.setString(2, split[1]);
            ps.setInt(3, Integer.parseInt(split[2]));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
