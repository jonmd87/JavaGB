package ru.gb.gerasimenko.chatroom.ChatServer;
import ru.gb.gerasimenko.chatroom.Helper.Defines;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;
import java.util.HashMap;
import java.util.Map;

public class UserList {

    private Map<Integer, User> userList;

    public UserList() {
        this.userList = createUserList(Defines.MAX_STRING_LENTH.getVal());
    }

    private Map<Integer, User> createUserList(int num) {
        Map<Integer, User> userMap = new HashMap<>();
        for (int i = 0; i < num; i++) {
            final User temp = new User(Phrases.NICK.getEng() + i,
                                        Phrases.LOGIN.getEng() + i,
                                         Phrases.PASSWORD.getEng() + i);
            userMap.put(temp.hashCode(), temp);
        }
        return userMap;
    }

    public String getNickByHashCode(Integer hashCode) {
        final User user = (User) userList.get((Integer) hashCode);
        return user != null ? user.getNick() : null;
     }

    public Integer checkLoginPassword(String login, String password) {
        if (login != null && password != null) {
            for (Map.Entry entry : userList.entrySet()) {
               final User user = (User) entry.getValue();
               if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                   return (Integer) entry.getKey();
               }
            }
        }
        return null;
    }

    public boolean addUserToUserList(User userToAdd) {
        for (Map.Entry entry : userList.entrySet()) {
            final User user = (User) entry.getValue();
            if ((user.hashCodeNick() == userToAdd.hashCodeNick()) ||
                    (user.hashCodeLogin() == userToAdd.hashCodeLogin())) {
                return false;
            }
        }
        userList.put(userToAdd.hashCode(), userToAdd);
        return true;
    }

    public void printAll() {
        for (Map.Entry entry : userList.entrySet()) {
            User temp = (User) entry.getValue();
            System.out.printf("%d %s %s %s\n", entry.getKey(), temp.getNick(), temp.getLogin(), temp.getPassword());
        }
    }
}
