package ru.gb.gerasimenko.chatroom.ChatServer;

import java.util.HashMap;
import java.util.Map;

public class UserList {

    private Map<String, User> userList;

    public UserList() {
        this.userList = createUserList(10);
    }

    private Map<String, User> createUserList(int num) {
        Map<String, User> userMap = new HashMap<>();
        for (int i = 0; i < num; i++) {
            final String login;
            final String nick;
            final String password;
        }
        return userMap;
    }

    public boolean 
}
