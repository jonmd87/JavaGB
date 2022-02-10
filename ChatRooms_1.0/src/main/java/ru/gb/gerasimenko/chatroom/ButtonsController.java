package ru.gb.gerasimenko.chatroom;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import ru.gb.gerasimenko.chatroom.Helper.Buttons;
import javafx.fxml.FXML;
import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

import java.util.ArrayList;
import java.util.Arrays;

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
        generalTextArea = new TextArea();
        textField = new TextField();
        membersTextArea = new TextArea();
        chatClient = new ChatClient(this, this.dialogWindows);
        loggedIN = false;
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
            this.chatClient.close();
            System.exit(0);
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
                chatClient.sendMessage(enteredText + StrConsts.END_LINE.getStr());
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
        if (loggedIN) {
            return;
        }
        final String authMsg = this.dialogWindows.registrationWindow(lang);
        if (authMsg.length() > 1) {
            this.chatClient.sendMessage(authMsg);
        }
    }

    public void onAuthorizationClick(ActionEvent actionEvent) {
        if (loggedIN) {
            return;
        }
        final String authMsg = this.dialogWindows.loginWindow(lang);
        if (authMsg.length() > 1) {
            this.chatClient.sendMessage(authMsg);
        }
    }

    public void updateMemberList(ArrayList<String> data) {
        if (data != null && data.size() > 0) {
            this.memberList = data;
            this.membersTextArea.clear();
                for (String s : memberList) {
                    this.membersTextArea.appendText(s + StrConsts.END_LINE.getStr());
                }
        }
    }

    public void onPrvtMsg(ActionEvent actionEvent) {
        chatClient.sendMessage(this.dialogWindows.privateMessage(chatClient.getNick(), memberList, lang));
    }
}