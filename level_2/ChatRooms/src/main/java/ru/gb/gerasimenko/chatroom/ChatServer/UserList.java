package ru.gb.gerasimenko.chatroom.ChatServer;

import ru.gb.gerasimenko.chatroom.Helper.Defines;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;

import java.util.HashMap;
import java.util.Map;

public class UserList {

    private Map<String, User> userList;

    public UserList() {
        this.userList = createUserList(Defines.MAX_STRING_LENTH.getVal());
    }

    private Map<String, User> createUserList(int num) {
        Map<String, User> userMap = new HashMap<>();
        for (int i = 0; i < num; i++) {
            final String nick = Phrases.NICK.value((byte) 0) + i;
            final String login = Phrases.LOGIN.value((byte) 0) + i;
            final String password = Phrases.PASSWORD.value((byte) 0) + i;
            userMap.put(login, (new User(nick, login, password)));
        }
        return userMap;
    }

    public String getNickByLoginAndPassword(String login, String password) {
        if (login != null && password != null) {
            for (Map.Entry entry : userList.entrySet()) {
                if (entry.getKey().equals(login)) {
                    User temp = (User) entry.getValue();
                    if (temp.getPassword().equals(password)) {
                        return temp.getNick();
                    }
                }
            }
        }
        return null;
    }

    public boolean addUserToUserList(String nick, String login, String password) {
        if (login != null && password != null && nick != null) {
            if (!userList.containsKey(login)) {
                userList.put(login, (new User(nick, login, password)));
            }
        }
        return false;
    }
}
