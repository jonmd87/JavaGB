package ru.gb.gerasimenko;


import java.sql.*;

public class JdbcApp {
    private Connection connection;
    private Statement statement;
    private String nick = "nick";
    private String log = "log";
    private String pas = "pas";
    private String LINE = "--------------------------------------------------------------------------------";

    public static void main( String[] args ) {
        JdbcApp jdbcApp = new JdbcApp();
        try {
            jdbcApp.connect();
            jdbcApp.createTable();
            jdbcApp.dropTable();
//            jdbcApp.select();
//            jdbcApp.updateLogin( "nick2", jdbcApp.log);
//            jdbcApp.selectByNick("nick2");
//            jdbcApp.select();
        } finally {
            jdbcApp.disconnect();
        }
    }

    private void batchInsert() {
        try (final PreparedStatement preparedStatement = connection.prepareStatement("insert into users (nick, login, password) values (?, ?, ?)")) {
            for (int i = 0; i < 3; i++) {
                preparedStatement.setString(1, this.nick);
                preparedStatement.setString(2, this.log);
                preparedStatement.setInt(3, (this.pas + i).hashCode());
                preparedStatement.addBatch();
            }
            final int[] ints = preparedStatement.executeBatch();
            int bathces = 0;
            for (int i : ints) {
                bathces += i;
            }
            System.out.println("was isnsert " + bathces + " bathces.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateLogin(String nick, String newLogin) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement("update users set login = ? where nick = ?")) {
            preparedStatement.setString(1, newLogin);
            preparedStatement.setString(2, nick);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void selectByNick(String nickOrigin) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                                    "select * from users where nick like ?")){
            preparedStatement.setString(1, nickOrigin);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.printf("%-10s %-10s %-10s %-10s\n", "id", "nick", "login", "password");
            System.out.println(LINE);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nick = resultSet.getString(2);
                String login = resultSet.getString(3);
                int password = resultSet.getInt(4);
                System.out.printf("%-10d %-10s %-10s %-10d\n", id, nick, login, password);
            }
            System.out.println("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void select() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from users;")) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            System.out.printf("%-10s %-10s %-10s %-10s\n", "id", "nick", "login", "password");
            System.out.println(LINE);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nick = resultSet.getString(2);
                String login = resultSet.getString(3);
                int password = resultSet.getInt(4);
                System.out.printf("%-10d %-10s %-10s %-10d\n", id, nick, login, password);
            }
            System.out.println("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropTable() {
        try {
            statement.executeUpdate("delete from users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insert(String nick, String log, int pas) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into users (nick, login, password) values (?, ?, ?)")) {
            preparedStatement.setString(1, nick);
            preparedStatement.setString(2, log);
            preparedStatement.setInt(3, pas);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        try {
            statement.executeUpdate("" +
                    "create table if not exists users (" +
                    "id integer primary key autoincrement, " +
                    "nick text, " +
                    "login text, " +
                    "password integer" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
