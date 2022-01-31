package ru.gb.gerasimenko.chatroom.ChatServer;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import ru.gb.gerasimenko.chatroom.Helper.Buttons;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;

import java.util.Optional;

public class DialogWindows {

    private int padding = 10;

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

    public boolean loginWindow(boolean registration, byte lang) {
        Dialog<ButtonType> loginWindow = new Dialog<>();
        loginWindow.setTitle(Buttons.AUTHORIZATION.value(lang));
        loginWindow.setHeaderText(Phrases.NEED_AUTHOR.value(lang));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(padding, padding, padding, padding));
        grid.setHgap(padding / 2);
        grid.setVgap(padding / 2);

        if (registration) {
            Label nickLabel = new Label(Phrases.NICK.value(lang));
            GridPane.setConstraints(nickLabel, 0, 0);
            grid.getChildren().add(nickLabel);
            TextField nickTextField = new TextField();
            GridPane.setConstraints(nickTextField, 0, 1);
            grid.getChildren().add(nickTextField);
        }

        Label loginLabel = new Label(Phrases.LOGIN.value(lang));
        GridPane.setConstraints(loginLabel, 1, 0);
        grid.getChildren().add(loginLabel);

        TextField logiTextField = new TextField();
        GridPane.setConstraints(logiTextField, 1, 1);
        grid.getChildren().add(logiTextField);

        Label passLabel = new Label(Phrases.PASSWORD.value(lang));
        GridPane.setConstraints(passLabel, 2, 0);
        grid.getChildren().add(passLabel);

        PasswordField passwordField = new PasswordField();
        GridPane.setConstraints(passwordField, 2, 1);
        grid.getChildren().add(passwordField);

        ButtonType buttonOk = new ButtonType(Buttons.OK.value(lang));
        ButtonType buttonCancel = new ButtonType(Buttons.CANCEL.value(lang));

        ButtonType buttonRegistration = new ButtonType(Buttons.REGISTRATION.value(lang));

        loginWindow.getDialogPane().setContent(grid);
        loginWindow.getDialogPane().getButtonTypes().setAll(buttonOk, buttonCancel, buttonRegistration);
        if (registration) {
            loginWindow.getDialogPane().getButtonTypes().removeAll(buttonRegistration);
        }
        Optional<ButtonType> result = loginWindow.showAndWait();
        if (result.get() == buttonOk) {
            System.out.println(buttonOk.getText());
        } else if (result.get() == buttonRegistration) {
            this.loginWindow(true, lang);
        }
        return false;
    }

}