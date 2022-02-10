package ru.gb.gerasimenko.chatroom.server.ServerRequestHandlers;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;
import ru.gb.gerasimenko.chatroom.Interfaces.ServerRequestHandler;
import ru.gb.gerasimenko.chatroom.server.ChatServer;

import java.util.HashMap;

public class AuthenticationHandlerServer implements ServerRequestHandler {
    private HashMap<String, User> userList;

    public AuthenticationHandlerServer() {
        createUsers();
    }

    private void createUsers() {
        this.userList = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            final User temp = new User(Phrases.NICK.getEng() + i,
                    Phrases.LOGIN.getEng() + i,
                    (Phrases.PASSWORD.getEng() + i).hashCode());
            this.userList.put(temp.getLogin(), temp);
        }
    }

    @Override
    public String handler(String data, ChatServer server) {
        String[] split = data.split(Commands.ARG_SEPARATOR.getStr());
        String login = split[0];
        Integer password = Integer.parseInt(split[1]);
        User temp = this.userList.get(login);
        return (temp != null && temp.getPassword().equals(password)) ? temp.getNick() : null;
    }

    // PRIVATE CLASS USER
    private static class User {
        private String nick;
        private String login;
        private Integer password;

        public User(String nick, String login, Integer password) {
            this.nick = nick;
            this.login = login;
            this.password = password;
        }

        public String getNick() {
            return nick;
        }

        public String getLogin() {
            return login;
        }

        public Integer getPassword() {
            return password;
        }
    }
}
