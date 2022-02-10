package ru.gb.gerasimenko.chatroom.server;

import ru.gb.gerasimenko.chatroom.Helper.StrConsts;

public class ServerStarting {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        System.out.println(StrConsts.SERV_RUNNING.getStr());
        chatServer.start();
        System.out.println(StrConsts.SESSION_CLOSED.getStr());
    }
}
