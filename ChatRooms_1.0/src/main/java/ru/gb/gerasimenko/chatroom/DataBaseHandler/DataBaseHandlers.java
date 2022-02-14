package ru.gb.gerasimenko.chatroom.DataBaseHandler;

import ru.gb.gerasimenko.chatroom.Helper.DBCommands;
import ru.gb.gerasimenko.chatroom.Interfaces.DataBaseRequestHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DataBaseHandlers {
    final private Map<String, DataBaseRequestHandler> dbHandler;
    private Connection connection;

    public DataBaseHandlers() {
        this.dbHandler = new HashMap<>();
        connect();
        initDataBaseHandler();
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:java.db");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void initDataBaseHandler() {
//        dbHandler.put(DBCommands.CREATE.getCmd(), new )
    }

}
