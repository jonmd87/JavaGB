package ru.gb.gerasimenko.chatroom;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import ru.gb.gerasimenko.chatroom.Helper.Buttons;
import javafx.fxml.FXML;
import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

import java.util.ArrayList;
import java.util.Arrays;

public class ButtonsController {
    @FXML
    private ListView<String> listView;
    @FXML
    public Menu menuFile;
    @FXML
    public MenuItem authorization;
    @FXML
    public MenuItem registration;
    @FXML
    public MenuItem language;
    @FXML
    public MenuItem exit;
    @FXML
    public TextArea generalTextArea;
    @FXML
    public TextField textField;
    @FXML
    public Button send;
    @FXML
    public Button privateMsg;
    @FXML
    public Label membersListLabel;
   // @FXML private MenuItem singOUT; //[возможно решится когда будем проходить THREAD] скрыл потому-что при|Authorisation|->|Sing out|->|Authorisation| выскакивает ошибка ->Not on FX application thread; currentThread = Thread-2

    private byte lang = 0;
    private boolean loggedIN = false;
    private final DialogWindows dialogWindows = new DialogWindows();
    private final ChatClient chatClient;
    private final ArrayList<String> memberList = new ArrayList<>();

    public ButtonsController() {
        chatClient = new ChatClient(this, this.dialogWindows);
    }

    public void initButtons() {
        menuFile.setText(Buttons.FILE.value(this.lang));
        authorization.setText(Buttons.AUTHORIZATION.value(this.lang));
        registration.setText(Buttons.REGISTRATION.value(this.lang));
       // singOUT.setText(Buttons.SING_OUT.value(this.lang)); ////[возможно решится когда будем проходить THREAD] скрыл потому-что при|Authorisation|->|Sing out|->|Authorisation| выскакивает ошибка ->Not on FX application thread; currentThread = Thread-2
        language.setText(Buttons.LANGUAGE.value(this.lang));
        exit.setText(Buttons.EXIT.value(this.lang));
        send.setText(Buttons.SEND.value(this.lang));
        privateMsg.setText(Buttons.PRIVATE_MSG.value(this.lang));
        membersListLabel.setText(Buttons.ONLINE.value(this.lang));
    }

    public void onSendButtonClick(ActionEvent actionEvent) {
        if (loggedIN) {
            if (!this.textField.getText().isEmpty()) {
                String enteredText = Commands.BROADCAST.getStr() + Commands.CMD_SEPARATOR.getStr() +
                                        Commands.BROADCAST.getStr() + Commands.ARG_SEPARATOR.getStr() +
                                            this.chatClient.getNick() + ": " + this.textField.getText();
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

    public void addMessage(String message) {
        generalTextArea.appendText(message + StrConsts.END_LINE.getStr());
    }

    public void onRegistrationClick(ActionEvent actionEvent) {
        final String authMsg = this.dialogWindows.registrationWindow(lang);
        if (authMsg.length() > 1) {
            this.chatClient.sendMessage(authMsg);
        }
    }

    public void onAuthorizationClick(ActionEvent actionEvent) {
        final String authMsg = this.dialogWindows.singINWindow(lang);
        if (authMsg.length() > 1) {
            this.chatClient.sendMessage(authMsg);
        }
    }

    public void updateMemberList(String[] data) {
        if (data != null) {
            this.memberList.clear();
            this.memberList.addAll(Arrays.asList(data));
            this.listView.getItems().clear();
            this.listView.getItems().addAll(data);
        }
    }

    public void onPrvtMsg(ActionEvent actionEvent) {
        if (loggedIN) {
            final String message = dialogWindows.privateMessage(chatClient.getNick(), "", memberList, lang);
            chatClient.sendMessage(message);
        }
    }

    public void onListViewClick(MouseEvent mouseEvent) {
        if (this.memberList.size() > 1 && mouseEvent.getClickCount() == 2) {
           final String recipient = listView.getSelectionModel().getSelectedItem();
           final String message = dialogWindows.privateMessage(chatClient.getNick(), recipient, memberList, lang);
           chatClient.sendMessage(message);
        }
    }

    public void onExitClick(ActionEvent actionEvent) {
        if (loggedIN) {
            System.out.println("in onExitClick");
            onSingOutClick(actionEvent);
        }
        System.exit(0);
    }

    public void onSingOutClick(ActionEvent actionEvent) {
        if (dialogWindows.exitWindow(lang)) {
            chatClient.sendMessage(Commands.LOGOUT.getStr() +
                                    Commands.CMD_SEPARATOR.getStr() +
                                        chatClient.getNick());
            this.setAuth();
            generalTextArea.clear();
            listView.getItems().clear();
        }
    }

    public void setAuth() {
        this.loggedIN = !this.loggedIN;
        this.authorization.setVisible(!this.loggedIN);
        this.registration.setVisible(!this.loggedIN);
       // this.singOUT.setVisible(this.loggedIN);
        this.textField.setEditable(this.loggedIN);
    }

    public void setLang(byte language) {
        this.lang = language;
    }
    public byte getLang() {return lang;}
}