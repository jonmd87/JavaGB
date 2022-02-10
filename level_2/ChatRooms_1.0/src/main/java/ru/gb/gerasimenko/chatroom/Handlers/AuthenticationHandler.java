package ru.gb.gerasimenko.chatroom.Handlers;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;
import ru.gb.gerasimenko.chatroom.Interfaces.AuthenticationServices;
import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler;
import ru.gb.gerasimenko.chatroom.server.ChatServer;

import java.util.HashMap;

public class AuthenticationHandler implements RequestHandler, AuthenticationServices {
    private HashMap<String, User> userList;
    private static AuthTimer authTimer;

    public AuthenticationHandler() {
        createUsers();
        authTimer = null;
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
        String[] split = data.split(Commands.STR_SEPARATOR.getStr());
        return getNickByLoginPassword(split[0], Integer.parseInt(split[1]));
    }

    @Override
    public String getNickByLoginPassword(String login, Integer password) {
        User temp = this.userList.get(login);
        return (temp != null && temp.getPassword().equals(password)) ? temp.getNick() : null;
    }


    // PRIVATE CLASS AUTHTIMER
    private class AuthTimer {
        private static int seconds;
        private static boolean flag;

        public AuthTimer(int seconds) {
            this.flag = true;
            this.seconds = seconds;
            this.start();
        }

        public void start() {
            Thread authTimer = new Thread(() -> {
                while (flag && seconds > 0) {
                    try {
                        Thread.sleep(100);
                        System.out.println("seconds " + seconds);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    seconds--;
                }
            });
            authTimer.setDaemon(true);
            authTimer.start();
            stop();
        }

        public void stop() {
            flag = false;
        }

        public boolean isFlag() {
            return flag;
        }

        public int getSeconds() {
            return seconds;
        }
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
