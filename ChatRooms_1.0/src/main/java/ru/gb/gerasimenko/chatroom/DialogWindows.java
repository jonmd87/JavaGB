package ru.gb.gerasimenko.chatroom;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import ru.gb.gerasimenko.chatroom.Helper.*;

import java.util.ArrayList;
import java.util.Optional;

public class DialogWindows {
    private final int padding = DgtlConsts.PADDING.value();


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
        alert.setContentText(Phrases.AREYOUSHURE.value(lang));
        ButtonType buttonOk = new ButtonType(Buttons.OK.value(lang));
        ButtonType buttonCancel = new ButtonType(Buttons.CANCEL.value(lang));
        alert.getButtonTypes().setAll(buttonOk, buttonCancel);
        Optional<ButtonType> result = alert.showAndWait();
        return (result.get() == buttonOk) ? true : false;
    }

    public String singINWindow(byte lang) {
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
               if (checkEnteredData(Commands.AUTH_IN.getStr(), loginFld.getText(), passFld.getText(), lang)) {
                   answer += Commands.AUTH_IN.getStr() +
                                Commands.CMD_SEPARATOR.getStr() +
                                    loginFld.getText() +
                                        Commands.ARG_SEPARATOR.getStr() +
                                            passFld.getText().hashCode();
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
                    alertWindow(Phrases.ALERT.value(lang), Phrases.AFTER_REGISTER_MSG.value(lang), "");
                    answer += Commands.REGISTRATION.getStr() +
                                Commands.CMD_SEPARATOR.getStr() +
                                    nickTxtFld +
                                        Commands.ARG_SEPARATOR.getStr() +
                                            loginLbl +
                                                Commands.ARG_SEPARATOR.getStr() +
                                                    passFld.getText().hashCode();
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
        if (nick.contains(" ") || nick.length() < DgtlConsts.MIN_NICK_LENTH.value()) {
            alarm += Phrases.WRONG_DATA.value(lang) + Phrases.NICK.value(lang) + StrConsts.END_LINE.getStr();
        }
        if (login.contentEquals("@.") || login.length() < DgtlConsts.MIN_LOGPASS_LEN.value()) {
            alarm += Phrases.WRONG_DATA.value(lang) + Phrases.LOGIN.value(lang) + StrConsts.END_LINE.getStr();
        }
        if (password.contentEquals("./,*&^%$#@!)(=+") || password.length() < DgtlConsts.MIN_LOGPASS_LEN.value()) {
            alarm += Phrases.WRONG_DATA.value(lang) + Phrases.PASSWORD.value(lang);
        }

        if (alarm.length() > 1) {
            this.alertWindow(Phrases.ALERT.value(lang), Phrases.ALERT.value(lang), alarm);
        }
        return (alarm.length() < 1) ? true : false;
    }

    public String privateMessage(String author, String recipient, ArrayList<String> data, byte lang) {
        String answer = "";
        Dialog<ButtonType> window = new Dialog<>();
        window.setTitle(Buttons.PRIVATE_MSG.value(lang));
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(padding, padding, padding, padding));
        grid.setHgap(padding / 2);
        grid.setVgap(padding / 2);

        Label rule = new Label(Phrases.RULE.value(lang));
        GridPane.setConstraints(rule, 0, 0);
        grid.getChildren().add(rule);

        Label sendToLbl = new Label(Phrases.RECIPIENTS.value(lang));
        GridPane.setConstraints(sendToLbl, 0, 1);
        grid.getChildren().add(sendToLbl);

        TextField sendtoFld = new TextField();
        sendtoFld.setText(recipient);
        sendtoFld.setPrefWidth(200);
        GridPane.setConstraints(sendtoFld, 0, 2);
        grid.getChildren().add(sendtoFld);

        Label txtLbl = new Label(Buttons.PRIVATE_MSG.value(lang));
        GridPane.setConstraints(txtLbl, 0, 3);
        grid.getChildren().add(txtLbl);

        TextField txtFld = new TextField();
        txtFld.setPrefWidth(500);
        GridPane.setConstraints(txtFld, 0, 4);
        grid.getChildren().add(txtFld);


        ButtonType buttonSend = new ButtonType(Buttons.SEND.value(lang));
        ButtonType buttonCancel = new ButtonType(Buttons.CANCEL.value(lang));

        window.getDialogPane().setContent(grid);
        window.getDialogPane().getButtonTypes().setAll(buttonSend, buttonCancel);

        while (true) {
            Optional<ButtonType> res = window.showAndWait();
            if (res.get() == buttonCancel) {
                break;
            } else if (res.get() == buttonSend && !txtFld.getText().isEmpty() &&
                                    checkFieldSendTo(data, sendtoFld.getText())) {
                final String message = Commands.TARGED_DELIVERY.getStr() +
                                        Commands.CMD_SEPARATOR.getStr() + author + " " + txtFld.getText() +
                                            Commands.ARG_SEPARATOR.getStr() + author + Commands.STR_SEPARATOR.getStr() +
                                                sendtoFld.getText().replaceAll(StrConsts.COMMA.getStr(), Commands.STR_SEPARATOR.getStr());
                answer = message;
                break;
            }
            alertWindow(Phrases.ALERT.value(lang), Phrases.WRONG_DATA.value(lang), "");
        }
        return answer;
    }

    private boolean checkFieldSendTo(ArrayList<String> data, String sendTo) {
        for (String s : sendTo.split(StrConsts.COMMA.getStr())) {
            if (!data.contains(s)) {
                return false;
            }
        }
        return true;
    }
}
