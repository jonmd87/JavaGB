package ru.gb.gerasimenko.chatroom.server;


import ru.gb.gerasimenko.chatroom.Helper.StrConsts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {
    private Connection connection;
    private Statement statement;

    public DataBaseConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:chatUsers.db");
            createStatement();
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DataBaseConnection(String url) {
        try {
            connection = DriverManager.getConnection(url);
            createStatement();
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            statement.executeUpdate("" +
                    "create table if not exists " + StrConsts.CHATUSERS.getStr() + "(" +
                    " id integer primary key autoincrement," +
                    " nick text not null unique," +
                    " login text not null unique," +
                    " password integer not null" +
                    ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createStatement() throws SQLException {
        if (this.connection != null) {
            connection.setAutoCommit(true);
            statement = connection.createStatement();
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

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
