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
    private HistoryControl historyControl;
    private String nick;

    public ClientHandler(ChatServer chatServer, ServerSocket serverSocket) {
        this.nick = null;
        this.chatServer = chatServer;
        this.participant = new ChatParticipant(serverSocket);
        Thread current =  new Thread(() -> {
            if (authentication()) {
                listeningNet();
            } else {
                sendMessage(Commands.NOTIFICATION.getStr() +
                                Commands.ARG_SEPARATOR.getStr() +
                                    Phrases.TIME_EXPIRED.ordinal() +
                                        Commands.STR_SEPARATOR.getStr() + "");
            }
            if (participant.connectionActive()) {
                try {
                    participant.sendMessage(Commands.LOGOUT.getStr() +
                                                Commands.ARG_SEPARATOR.getStr() +
                                                    StrConsts.END_LINE.getStr());
                    participant.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
                this.nick = chatServer.distribution(this.participant.readMessage());
                if (Commands.DB_REGISTER.getStr().equals(this.nick)) {
                    this.nick = null;
                } else if (!chatServer.subscribe(this)) {
                    participant.sendMessage(Commands.NOTIFICATION.getStr() +
                                                Commands.ARG_SEPARATOR.getStr() +
                                                    Phrases.WRONG_AUTH.ordinal() +
                                                       Commands.STR_SEPARATOR.getStr() + " ");
                    this.nick = null;
                }
            }
            timer.blockTimer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (this.nick != null);
    }


    private void listeningNet() {
        try {
            sendInitializationData();
            while (this.participant.connectionActive()) {
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

    private void sendInitializationData() {
        this.sendMessage(Commands.AUTH_IN.getStr() +
                Commands.ARG_SEPARATOR.getStr() + this.nick);
        chatServer.sendUserList();
        this.historyControl = new HistoryControl(this.nick);
        this.sendMessage(Commands.BROADCAST.getStr() +
                            Commands.ARG_SEPARATOR.getStr() +
                                this.historyControl.getLastLines(DgtlConsts.HUNDRED.value()));

    }

    public void sendMessage(String message) {
        if (this.participant.connectionActive()) {
            try {
                System.out.println("ClientHandler.in sendMessage " + message + "|");
                this.participant.sendMessage(message);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
        System.out.println("new nick " + nick);
    }

    public HistoryControl getHistoryControl() {
        return historyControl;
    }

    public ChatParticipant getParticipant() {
        return participant;
    }
}
