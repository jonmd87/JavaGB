package ru.gb.gerasimenko.chatroom.Interfaces;

public interface AuthenticationServices {
    public String getNickByLoginPassword(String login, Integer password);
}
