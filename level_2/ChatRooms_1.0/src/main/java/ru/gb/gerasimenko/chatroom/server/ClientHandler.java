package ru.gb.gerasimenko.chatroom.server;

import ru.gb.gerasimenko.chatroom.ChatParticipant;
import ru.gb.gerasimenko.chatroom.Handlers.Handler;
import ru.gb.gerasimenko.chatroom.Helper.Commands;

import java.io.IOException;
import java.net.ServerSocket;

public class ClientHandler {
    private ChatServer chatServer;
    private ChatParticipant participant;
    private String nick;

    public ClientHandler(ChatServer chatServer, ServerSocket serverSocket) {
        this.nick = null;
        this.chatServer = chatServer;
        this.participant = new ChatParticipant(serverSocket);
        new Thread(() -> {
            if (authentication()) {
                listeningNet();
            }
            chatServer.unsubscribe(this);
        }).start();
    }

    private boolean authentication() {
        try {
            while (nick == null) {
                final String message = this.participant.readMessage();
                this.nick = chatServer.distribution(message);
                if (!chatServer.subscribe(this)) {
                    this.nick = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (this.nick != null);
    }


    private void listeningNet() {
        try {
            this.participant.sendMessage(Commands.AUTH_IN.getStr() + Commands.STR_SEPARATOR.getStr() + this.nick);
            while (this.participant.connectionActive()) {
                System.out.println("listening net");
                final String message = this.participant.readMessage();
                chatServer.distribution(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            if (this.participant.connectionActive()) {
                this.participant.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }
}
