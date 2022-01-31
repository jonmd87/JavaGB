package ru.gb.gerasimenko.chatroom.ChatServer;

public interface AuthService {
    public String getNickByLoginAndPassword(String login, String password);
}
