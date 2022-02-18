package ru.gb.gerasimenko;

import java.sql.*;

public class JdbcApp {
    private Connection connection;
    private Statement statement;

    public static void main(String[] args) {
        final JdbcApp jdbcApp = new JdbcApp();
        try {
            jdbcApp.connect();
            jdbcApp.createTable();
            jdbcApp.dropTable();
            for (int i = 0; i < 5; i++) {
                jdbcApp.insert("Nick" + i, "Login" + i, ("Password" + i).hashCode());
            }
            jdbcApp.select();
            System.out.println("======================================");
        } catch (SQLException sqlExp) {
            sqlExp.printStackTrace();
        }
        finally {
            jdbcApp.disconnect();
        }
    }

    private void update(String name, int score) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("" +
                "update users set score = ? where name = ?");
        ps.setInt(1, score);
        ps.setString(2, name);
        ps.executeUpdate();
    }

    private String selectBy(String login, int password) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                        "select * from users where login = ? and password = ?");
        ps.setString(1, login);
        ps.setInt(2, password);
        final ResultSet resultSet = ps.executeQuery();
        return (resultSet.next()) ? resultSet.getString(2) : null;
    }

    private void select() throws SQLException {
        final ResultSet resultSet = statement.executeQuery("select * from users");
        while (resultSet.next()) {
            System.out.printf("%-10d %-10s %-10s %-10d\n", resultSet.getInt(1),
                                                        resultSet.getString(2),
                                                        resultSet.getString(3),
                                                            resultSet.getInt(4));
        }
    }

    private void dropTable() throws SQLException {
            statement.executeUpdate("delete from users");
    }

    private void insert(String nick, String login, int password) throws SQLException {
        PreparedStatement prepare = connection.prepareStatement(
                "insert into users (nick, login, password) values (?, ?, ?)");
        prepare.setString(1, nick);
        prepare.setString(2, login);
        prepare.setInt(3, password);
        prepare.executeUpdate();
    }

    private void createTable() throws SQLException {
        statement.executeUpdate("" +
                "create table if not exists users (" +
                " id integer primary key autoincrement," +
                " nick text not null unique," +
                " login text not null unique," +
                " password integer not null" +
                ");");
    }

    private void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:java.db");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
