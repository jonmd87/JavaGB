package ru.gb.gerasimenko.chatroom.client;

import ru.gb.gerasimenko.chatroom.ButtonsController;
import ru.gb.gerasimenko.chatroom.ChatParticipant;
import ru.gb.gerasimenko.chatroom.DialogWindows;
import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.DgtlConsts;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ChatClient {
    private final ChatParticipant participant;
    private final ButtonsController buttonsController;
    private final DialogWindows dialogWindows;
    private String nick;


    public ChatClient(ButtonsController buttonsController, DialogWindows dialogWindows) {
        this.buttonsController = buttonsController;
        this.dialogWindows = dialogWindows;
        this.participant = new ChatParticipant(StrConsts.LOCALHOST.getStr(), DgtlConsts.PORT.value());
        Thread connection =  new Thread(() -> {
                openConnection();
        });
        connection.setDaemon(true);
        connection.start();
    }

    private void openConnection() {
        try {
            while (this.participant.connectionActive()) {
                String message = this.participant.readMessage();
                if (Commands.LOGOUT.getStr().equals(message)) {
                    this.participant.close();
                    break;
                } else if (message.startsWith(Commands.AUTH_IN.getStr())) {
                    authorizationPassed(message);
                    continue;
                } else if (message.startsWith(Commands.NEW_MEMBER.getStr())) {
                    message = message.split(Commands.NEW_MEMBER.getStr())[1];
                    message += Phrases.ENETERED_IN_CHAT.value(buttonsController.getLang());
                }
                else if (message.startsWith(Commands.MEMBER_LEFT.getStr())) {
                    message = createMessage(message);
                } else if (message.startsWith(Commands.UPDATE_USERLIST.getStr())) {
                    updateUserList(message);
                    continue;
                }
                System.out.println("message for textArea " + message + "|");
                this.buttonsController.addMessage(message);
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    private void updateUserList(String message) {
        String[] split = message.split(Commands.STR_SEPARATOR.getStr());
        ArrayList<String> userList = new ArrayList<>(Arrays.asList(split));
        System.out.println(userList);
        userList.remove(Commands.UPDATE_USERLIST.getStr());
        buttonsController.updateMemberList(userList);
    }

    private String createMessage(String message) {
        String[] split = message.split(Commands.STR_SEPARATOR.getStr());
        String answer = split[1] + Phrases.LEAVE_CHAT.value(buttonsController.getLang());
        return answer;
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

    public void authorizationPassed(String message) throws IOException {
        String[] split = message.split(Commands.STR_SEPARATOR.getStr());
        this.nick = split[1];
        String answer = Commands.BROADCAST.getStr() + Commands.CMD_SEPARATOR.getStr() +
                Commands.NEW_MEMBER.getStr() + this.nick;
        participant.sendMessage(answer);
        buttonsController.loggedIn();
    }

    public void close() {
        try {
            System.out.println(nick);
            participant.sendMessage(Commands.LOGOUT.getStr() + Commands.CMD_SEPARATOR.getStr() + nick);
            participant.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }
}
