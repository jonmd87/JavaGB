package ru.gb.gerasimenko.chatroom;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import ru.gb.gerasimenko.chatroom.Helper.Buttons;
import javafx.fxml.FXML;
import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

import java.util.ArrayList;

public class ButtonsController {
    @FXML public Menu menuFile;
    @FXML public MenuItem authorization;
    @FXML public MenuItem registration;
    @FXML public MenuItem language;
    @FXML public MenuItem exit;
    @FXML public TextArea generalTextArea;
    @FXML public TextField textField;
    @FXML public TextArea membersTextArea;
    @FXML public Button send;
    @FXML public Button prvtMsg;
    @FXML public Label membersListLabel;

    private byte lang = 0;
    private DialogWindows dialogWindows;
    private final ChatClient chatClient;
    private boolean loggedIN;
    private ArrayList<String> memberList;

    public ButtonsController() {
        dialogWindows = new DialogWindows();
      //  lang = dialogWindows.chooseLanguage();
        menuFile = new Menu(Buttons.FILE.value(this.lang));
        authorization = new MenuItem(Buttons.AUTHORIZATION.value(this.lang));
        registration = new MenuItem(Buttons.REGISTRATION.value(this.lang));
        language = new MenuItem(Buttons.LANGUAGE.value(this.lang));
        exit = new MenuItem(Buttons.EXIT.value(this.lang));
        send = new Button(Buttons.SEND.value(this.lang));
        prvtMsg = new Button(Buttons.PRIVATE_MSG.value(this.lang));
        membersListLabel = new Label(Buttons.ONLINE.value(this.lang));
        membersTextArea = new TextArea();
        generalTextArea = new TextArea();
        textField = new TextField();
        chatClient = new ChatClient(this, this.dialogWindows, Thread.currentThread());
        loggedIN = false;
        memberList = new ArrayList<>();
    }

    public void initButtons() {
        menuFile.setText(Buttons.FILE.value(this.lang));
        authorization.setText(Buttons.AUTHORIZATION.value(this.lang));
        registration.setText(Buttons.REGISTRATION.value(this.lang));
        language.setText(Buttons.LANGUAGE.value(this.lang));
        exit.setText(Buttons.EXIT.value(this.lang));
        send.setText(Buttons.SEND.value(this.lang));
        prvtMsg.setText(Buttons.PRIVATE_MSG.value(this.lang));
        membersListLabel.setText(Buttons.ONLINE.value(this.lang));
    }

    public void onExitClick(ActionEvent actionEvent) {
        if (dialogWindows.exitWindow(lang)) {
            String answer = Commands.LOGOUT.getStr() + Commands.CMD_SEPARATOR.getStr() +
                    chatClient.getNick();
            System.out.println(answer + "| in onExit click");
            chatClient.sendMessage(answer);
        }
    }

    public  void setLang(byte language) {
        this.lang = language;
    }

    public void onSendButtonClick(ActionEvent actionEvent) {
        if (loggedIN) {
            String enteredText = Commands.BROADCAST.getStr() + Commands.CMD_SEPARATOR.getStr();
            enteredText += this.chatClient.getNick() + ": " + this.textField.getText();
            if (enteredText != null && !enteredText.isEmpty()) {
                chatClient.sendMessage(enteredText);
            }
        }
        textField.clear();
        textField.requestFocus();
    }

    public void onLanguageClick(ActionEvent actionEvent) {
        this.setLang(this.dialogWindows.chooseLanguage());
        initButtons();
    }

    public byte getLang() {
        return lang;
    }

    public void loggedIn() {
        this.loggedIN = true;
    }

    public void addMessage(String message) {
        generalTextArea.appendText(message + StrConsts.END_LINE.getStr());
    }

    public void onRegistrationClick(ActionEvent actionEvent) {
        System.out.println("loggenid = " + this.loggedIN);
        if (!loggedIN) {
            final String authMsg = this.dialogWindows.registrationWindow(lang);
            if (authMsg.length() > 1) {
                this.chatClient.sendMessage(authMsg);
            }
        }
    }

    public void onAuthorizationClick(ActionEvent actionEvent) {
        System.out.println("log genid = " + this.loggedIN);
        if (!loggedIN) {
            final String authMsg = this.dialogWindows.loginWindow(lang);
            if (authMsg.length() > 1) {
                this.chatClient.sendMessage(authMsg);
            }
        }
    }

    public void updateMemberList(ArrayList<String> data) {
        System.out.println("inUpadateUserlist");
        if (data != null) {
            if (data.size() > this.memberList.size()) {broadcastNewMemberJoined(data);}
            else if (data.size() < this.memberList.size()) {broadcastMemberLeftChat(data);}
            this.memberList = data;
            this.membersTextArea.clear();
                for (String s : memberList) {
                    this.membersTextArea.appendText(s + StrConsts.END_LINE.getStr());
                }

        }
    }

    private void broadcastMemberLeftChat(ArrayList<String> data) {
        for (String s : this.memberList) {
            if (!data.contains(s)) {
                this.addMessage(s + " " + Phrases.LEAVE_CHAT.value(lang));
            }
        }
    }

    private void broadcastNewMemberJoined(ArrayList<String> data) {
       for (String s : data) {
           if (!this.memberList.contains(s)) {
               this.addMessage(s + " " + Phrases.ENETERED_IN_CHAT.value(lang));
           }
       }
    }

    public void onPrvtMsg(ActionEvent actionEvent) {
        chatClient.sendMessage(this.dialogWindows.privateMessage(chatClient.getNick(), memberList, lang));
    }
}