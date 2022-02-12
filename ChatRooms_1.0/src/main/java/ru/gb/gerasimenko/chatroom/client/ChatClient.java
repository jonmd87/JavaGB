package ru.gb.gerasimenko.chatroom.client;

import ru.gb.gerasimenko.chatroom.ButtonsController;
import ru.gb.gerasimenko.chatroom.ChatParticipant;
import ru.gb.gerasimenko.chatroom.DialogWindows;
import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.DgtlConsts;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;
import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler_Client;
import ru.gb.gerasimenko.chatroom.client.ClientRequestHandler.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChatClient {
    private ChatParticipant participant;
    private final ButtonsController buttonsController;
    private final DialogWindows dialogWindows;
    private final Map<String, RequestHandler_Client> handler;
    private String nick;


    public ChatClient(ButtonsController buttonsController, DialogWindows dialogWindows) {
        this.buttonsController = buttonsController;
        this.dialogWindows = dialogWindows;
        this.handler = new HashMap<>();
        initHandler();
        Thread connection =  new Thread(this::openConnection);
        connection.setDaemon(true);
        connection.start();
    }

    private void openConnection() {
        try {
            while (true) {
                this.participant = new ChatParticipant(StrConsts.LOCALHOST.getStr(), DgtlConsts.PORT.value());
                while (this.participant.connectionActive()) {
                    String message = this.participant.readMessage();
                    System.out.println("incomingMSG CLIENT-->|" + message + "|<--");
                    String[] split = message.split(Commands.ARG_SEPARATOR.getStr());
//                    for (String s : split) {
//                        System.out.println("-" + s + "-");
//                    }
                    RequestHandler_Client tempHandler = handler.get(split[0]);
                    tempHandler.handler(split[1], this);
                }
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void sendMessage(String message){
        try {
            System.out.println(message + " in send message");
            if (!message.isEmpty()) {
                this.participant.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initHandler() {
        this.handler.put(Commands.AUTH_IN.getStr(), new RequestHandler_ClientAUTH());
        this.handler.put(Commands.UPDATE_USERLIST.getStr(), new RequestHandler_ClientUpdateUsrList());
        this.handler.put(Commands.LOGOUT.getStr(), new RequestHandler_ClientLogout());
        this.handler.put(Commands.BROADCAST.getStr(), new RequestHandler_ClientBroadcast());
        this.handler.put(Commands.ERROR.getStr(), new RequestHandler_ClientERROR());
        this.handler.put(Commands.USER_MOVEMENTS.getStr(), new RequestHandler_ClientUserMovement());
    }

    public ButtonsController getButtonsController() {return buttonsController;}
    public ChatParticipant getParticipant() {return participant;}
    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
      this.nick = nick;
    }
}
