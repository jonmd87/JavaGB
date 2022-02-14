package ru.gb.gerasimenko.chatroom.server;

import ru.gb.gerasimenko.chatroom.ChatParticipant;
import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.DgtlConsts;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;

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
        System.out.println("Client-->[" + this.hashCode() + "] AUTH");
        Thread current =  new Thread(() -> {
            if (authentication()) {
                listeningNet();
            } else {
                sendMessage(Commands.ERROR.getStr() + Commands.ARG_SEPARATOR.getStr() + Phrases.TIME_EXPIRED.ordinal());
            }
            if (participant.connectionActive()) {
                try {
                    participant.sendMessage(Commands.LOGOUT.getStr() + Commands.ARG_SEPARATOR.getStr() + StrConsts.END_LINE.getStr());
                    participant.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Client-->[" + this.hashCode() + "] LOGOUT");
        });
        current.setDaemon(true);
        current.start();
    }

    private boolean authentication() {
        try {
            AuthorizationTimer timer = new AuthorizationTimer(DgtlConsts.SEC_120.value());
            Thread timerThread = new Thread(timer);
            timerThread.setDaemon(true);
            timerThread.start();
            while (nick == null && timer.getFlag()) {
                System.out.println("Timer = " + timer.getTime() + timer.getFlag());
                this.nick = chatServer.distribution(this.participant.readMessage());
                if (!chatServer.subscribe(this)) {
                    participant.sendMessage(Commands.ERROR.getStr() +
                                                Commands.ARG_SEPARATOR.getStr() +
                                                    Phrases.WRONG_AUTH.ordinal());
                    this.nick = null;
                }
            }
            System.out.println("timer = " + timer.getTime() + " " + timer.getFlag());
            timer.blockTimer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (this.nick != null);
    }


    private void listeningNet() {
        try {
            this.sendMessage(Commands.AUTH_IN.getStr() + Commands.ARG_SEPARATOR.getStr() + this.nick);
            chatServer.sendUserList();
            while (this.participant.connectionActive()) {
                System.out.println("listening net");
                final String message = this.participant.readMessage();
                if (this.participant.connectionActive()) {
                    chatServer.distribution(message);
                }
            }
            this.participant.close();
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
