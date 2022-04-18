package ru.gb.gerasimenko.chatroom.server.ServerRequestHandlers;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;
import ru.gb.gerasimenko.chatroom.Interfaces.ServerRequestHandler;
import ru.gb.gerasimenko.chatroom.server.ChatServer;
import ru.gb.gerasimenko.chatroom.server.ClientHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteAccountHandlerServer implements ServerRequestHandler {

    @Override
    public String handler(String data, ChatServer server) {
        Connection connection = server.getDataBase().getConnection();
        String[] split = data.split(Commands.STR_SEPARATOR.getStr());
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "delete from " + StrConsts.CHATUSERS.getStr() + " where nick = ? login = ? password = ?;")) {
            preparedStatement.setString(1, split[0]);
            preparedStatement.setString(2, split[1]);
            preparedStatement.setInt(3, split[2].hashCode());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ClientHandler client = server.getClients().get(split[0]);
                client.sendMessage(Commands.LOGOUT.getStr() +
                        Commands.ARG_SEPARATOR.getStr() +
                        StrConsts.END_LINE.getStr());
                client.getHistoryControl().deleteHistoryFile();
                client.getParticipant().close();
                server.unsubscribe(data);
                server.sendUserList();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
