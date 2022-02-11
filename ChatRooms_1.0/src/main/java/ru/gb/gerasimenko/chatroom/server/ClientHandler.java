package ru.gb.gerasimenko.chatroom.server;

import ru.gb.gerasimenko.chatroom.ChatParticipant;
import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;

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
        Thread current =  new Thread(() -> {
            if (authentication()) {
                listeningNet();
            }
        });
        current.setDaemon(true);
        current.start();
    }

    private boolean authentication() {
        try {
            while (nick == null) {
                final String message = this.participant.readMessage();
                this.nick = chatServer.distribution(message);
                if (!chatServer.subscribe(this)) {
                    participant.sendMessage(Commands.ERROR.getStr() + Commands.ARG_SEPARATOR.getStr() + Phrases.WRONG_AUTH.ordinal());
                    this.nick = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sendMessage(Commands.AUTH_IN.getStr() + Commands.ARG_SEPARATOR.getStr() + this.nick);
        chatServer.sendUserList();
        return (this.nick != null);
    }


    private void listeningNet() {
        try {
            while (this.participant.connectionActive()) {
                System.out.println("listening net");
                final String message = this.participant.readMessage();
                if (this.participant.connectionActive()) {
                    chatServer.distribution(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        if (this.participant.connectionActive()) {
            try {
                this.participant.sendMessage(message);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public String getNick() {
        return nick;
    }

    public ChatParticipant getParticipant() {
        return participant;
    }
}
