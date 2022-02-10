package ru.gb.gerasimenko.chatroom;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import ru.gb.gerasimenko.chatroom.Helper.Buttons;
import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.DgtlConsts;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;

import java.util.ArrayList;
import java.util.Optional;

public class DialogWindows {

    private byte minNickLenght;
    private byte minLoginPasswordLenth;
    private int padding;
    private String cmdSepar;
    private String strSepar;
    private String auth_cmd;
    private String reg_cmd;
    private boolean loggenedIn;

    public DialogWindows() {
        loggenedIn = false;
        this.minNickLenght = (byte) DgtlConsts.MIN_NICK_LENTH.value();
        this.minLoginPasswordLenth = (byte) DgtlConsts.MIN_LOGPASS_LEN.value();
        this.padding = DgtlConsts.PADDING.value();
        this.cmdSepar = Commands.CMD_SEPARATOR.getStr();
        this.strSepar = Commands.ARG_SEPARATOR.getStr();
        this.auth_cmd = Commands.AUTH_IN.getStr();
        this.reg_cmd = Commands.REGISTRATION.getStr();
    }

    public byte chooseLanguage() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        ButtonType buttonEng = new ButtonType(Buttons.ENGLISH.value((byte)0));
        ButtonType buttonRus = new ButtonType(Buttons.RUSSIAN.value((byte) 1));

        alert.getButtonTypes().setAll(buttonEng, buttonRus);
        Optional<ButtonType> result = alert.showAndWait();
        return (byte) ((result.get() == buttonEng)? 0: 1);
    }

    public void alertWindow(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        ButtonType buttonOk = new ButtonType(Buttons.OK.value((byte) 0));
        alert.getButtonTypes().setAll(buttonOk);
        alert.showAndWait();
    }

    public boolean exitWindow(byte lang) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(Buttons.EXIT.value(lang));
        alert.setHeaderText(Phrases.CONFIRM_EXIT.value(lang));
        alert.setContentText(Phrases.AREYUSHURE.value(lang));
        ButtonType buttonOk = new ButtonType(Buttons.OK.value(lang));
        ButtonType buttonCancel = new ButtonType(Buttons.CANCEL.value(lang));
        alert.getButtonTypes().setAll(buttonOk, buttonCancel);
        Optional<ButtonType> result = alert.showAndWait();
        return (result.get() == buttonOk) ? true : false;
    }

    public String loginWindow(byte lang) {
        Dialog<ButtonType> loginWindow = new Dialog<>();
        loginWindow.setTitle(Buttons.AUTHORIZATION.value(lang));
        loginWindow.setHeaderText(Phrases.NEED_AUTHOR.value(lang));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(padding, padding, padding, padding));
        grid.setHgap(padding / 2);
        grid.setVgap(padding / 2);


        Label loginLabel = new Label(Phrases.LOGIN.value(lang));
        GridPane.setConstraints(loginLabel, 1, 0);
        grid.getChildren().add(loginLabel);

        TextField loginFld = new TextField();
        GridPane.setConstraints(loginFld, 1, 1);
        grid.getChildren().add(loginFld);

        Label passLabel = new Label(Phrases.PASSWORD.value(lang));
        GridPane.setConstraints(passLabel, 2, 0);
        grid.getChildren().add(passLabel);

        PasswordField passFld = new PasswordField();
        GridPane.setConstraints(passFld, 2, 1);
        grid.getChildren().add(passFld);

        ButtonType buttonOk = new ButtonType(Buttons.OK.value(lang));
        ButtonType buttonCancel = new ButtonType(Buttons.CANCEL.value(lang));
        ButtonType buttonRegistration = new ButtonType(Buttons.REGISTRATION.value(lang));

        loginWindow.getDialogPane().setContent(grid);
        loginWindow.getDialogPane().getButtonTypes().setAll(buttonOk, buttonCancel, buttonRegistration);
        String answer = "";
        while (true) {
            Optional<ButtonType> result = loginWindow.showAndWait();
            if (result.get() == buttonOk) {
               if (checkEnteredData(auth_cmd, loginFld.getText(), passFld.getText(), lang)) {
                   answer += auth_cmd + cmdSepar + loginFld.getText() + strSepar + passFld.getText().hashCode();
                   break;
               }
            } else if (result.get() == buttonRegistration) {
                answer = registrationWindow(lang);
            } else if (result.get() == buttonCancel) {
                break;
            }
        }
        return answer;
    }

    public String registrationWindow(byte lang) {
        Dialog<ButtonType> loginWindow = new Dialog<>();
        loginWindow.setTitle(Buttons.REGISTRATION.value(lang));
        loginWindow.setHeaderText(Phrases.NEW_USER.value(lang));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(padding, padding, padding, padding));
        grid.setHgap(padding / 2);
        grid.setVgap(padding / 2);


        Label nickLbl = new Label(Phrases.NICK.value(lang));
        GridPane.setConstraints(nickLbl, 0, 0);
        grid.getChildren().add(nickLbl);
        TextField nickTxtFld = new TextField();
        GridPane.setConstraints(nickTxtFld, 0, 1);
        grid.getChildren().add(nickTxtFld);

        Label loginLbl = new Label(Phrases.LOGIN.value(lang));
        GridPane.setConstraints(loginLbl, 1, 0);
        grid.getChildren().add(loginLbl);

        TextField loginTxtFld = new TextField();
        GridPane.setConstraints(loginTxtFld, 1, 1);
        grid.getChildren().add(loginTxtFld);

        Label passLbl = new Label(Phrases.PASSWORD.value(lang));
        GridPane.setConstraints(passLbl, 2, 0);
        grid.getChildren().add(passLbl);

        PasswordField passFld = new PasswordField();
        GridPane.setConstraints(passFld, 2, 1);
        grid.getChildren().add(passFld);

        ButtonType buttonOk = new ButtonType(Buttons.OK.value(lang));
        ButtonType buttonCancel = new ButtonType(Buttons.CANCEL.value(lang));

        loginWindow.getDialogPane().setContent(grid);
        loginWindow.getDialogPane().getButtonTypes().setAll(buttonOk, buttonCancel);
        Optional<ButtonType> result = loginWindow.showAndWait();
        String answer = "";
        while (true) {
            if (result.get() == buttonOk) {
                if (checkEnteredData(nickTxtFld.getText(), loginTxtFld.getText(), passFld.getText(), lang)){
                    alertWindow(Phrases.ALLERT.value(lang), Phrases.AFTER_REGISTER_MSG.value(lang), "");
                    answer += reg_cmd + cmdSepar + nickTxtFld + strSepar + loginLbl + strSepar + passFld.getText().hashCode();
                    break;
                }
            } else if (result.get() == buttonCancel) {
                break;
            }
        }
        return answer;
    }

    private boolean checkEnteredData(String nick, String login, String password, byte lang) {
        String alarm = "";
        if (nick.contains(" ") || nick.length() < minNickLenght) {
            alarm += "wrong data in field Nick" + "\n";
        }
        if (login.contentEquals("@.") || login.length() < minLoginPasswordLenth) {
            alarm += "wrong data in field Login"  + "\n";
        }
        if (password.contentEquals("./,*&^%$#@!)(=+") || password.length() < minLoginPasswordLenth) {
            alarm += "wrong data in field Password";
        }

        if (alarm.length() > 1) {
            this.alertWindow(Phrases.ALLERT.value(lang), Phrases.ALLERT.value(lang), alarm);
        }
        return (alarm.length() < 1) ? true : false;
    }

    public String privateMessage(String author, ArrayList<String> data, byte lang) {
        System.out.println("in privateMessage");
        String answer = "";
        if (data == null || data.size() == 0) {
            return answer;
        }
        System.out.println("in privatMessage in start " + data);
        Dialog<ButtonType> window = new Dialog<>();
        window.setTitle(Buttons.PRIVATE_MSG.value(lang));
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(padding, padding, padding, padding));
        grid.setHgap(padding / 2);
        grid.setVgap(padding / 2);

        Label sendToLbl = new Label(Phrases.WHO_SEND_TO.value(lang));
        GridPane.setConstraints(sendToLbl, 0, 0);
        grid.getChildren().add(sendToLbl);

        TextField sendtoFld = new TextField();
        sendtoFld.setPrefWidth(200);
        GridPane.setConstraints(sendtoFld, 0, 1);
        grid.getChildren().add(sendtoFld);

        Label txtLbl = new Label(Buttons.PRIVATE_MSG.value(lang));
        GridPane.setConstraints(txtLbl, 0, 2);
        grid.getChildren().add(txtLbl);

        TextField txtFld = new TextField();
        txtFld.setPrefWidth(500);
        GridPane.setConstraints(txtFld, 0, 3);
        grid.getChildren().add(txtFld);


        ButtonType buttonSend = new ButtonType(Buttons.SEND.value(lang));
        ButtonType buttonCancel = new ButtonType(Buttons.CANCEL.value(lang));

        window.getDialogPane().setContent(grid);
        window.getDialogPane().getButtonTypes().setAll(buttonSend, buttonCancel);

        while (true) {
            Optional<ButtonType> result = window.showAndWait();
            if (result.get() == buttonCancel) {
                break;
            } else if (result.get() == buttonSend && !sendtoFld.getText().isEmpty() &&
                    !txtFld.getText().isEmpty() && data.contains(sendtoFld.getText())) {
                    answer += Commands.SEND_TO.getStr() + Commands.CMD_SEPARATOR.getStr() +
                            sendtoFld.getText() + "from" + author + Commands.ARG_SEPARATOR.getStr() +
                            author + ": " + txtFld.getText();
                    break;
            }
        }
        System.out.println("in privateMessage " + answer);
        return answer;
    }
}
