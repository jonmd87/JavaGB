package ru.gb.gerasimenko.chatroom.server.ServerRequestHandlers;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;
import ru.gb.gerasimenko.chatroom.Interfaces.ServerRequestHandler;
import ru.gb.gerasimenko.chatroom.server.ChatServer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUpdateDate implements ServerRequestHandler {
    @Override
    public String handler(String data, ChatServer server) {
        System.out.println("IN UPDATE |" + data + "|");
        String[] split = data.split(Commands.STR_SEPARATOR.getStr());
        updateDate(split[0], split[1], server);
        return null;
    }

    private void updateDate(String old, String newD, ChatServer server) {
        String[] oldData = old.split(Commands.ARG_SEPARATOR.getStr());
        String[] newData = newD.split(Commands.ARG_SEPARATOR.getStr());
        System.out.println("old " + old);
        System.out.println("new " + newD);
        for (int i = 0; i < 3; i++) {
            System.out.println(oldData[i] + " " + newData[i]);
        }
        String updateStr = "update " + StrConsts.CHATUSERS.getStr() + " set nick = ?, login = ?, password = ? where nick = ? and login = ? and password = ?;";
        System.out.println("updateStr " + updateStr + "|");
        try (final PreparedStatement preparedStatement = server.getDataBase().getConnection().prepareStatement(updateStr)) {
            preparedStatement.setString(1, newData[0]); // nick
            preparedStatement.setString(2, newData[1]); // login
            preparedStatement.setString(3, newData[2]); // password
            preparedStatement.setString(4, oldData[0]); // nick
            preparedStatement.setString(5, oldData[1]); // login
            preparedStatement.setString(6, oldData[2]); // password
            preparedStatement.executeUpdate();
            server.updateUserData(oldData[0], newData[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
