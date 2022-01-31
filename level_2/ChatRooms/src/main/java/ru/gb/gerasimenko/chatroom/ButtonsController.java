package ru.gb.gerasimenko.chatroom;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import ru.gb.gerasimenko.chatroom.Helper.Buttons;
import javafx.fxml.FXML;
import ru.gb.gerasimenko.chatroom.Client.DialogWindows;

public class ButtonsController {
    @FXML public Menu file;
    @FXML public MenuItem authorization;
    @FXML public Menu language;
    @FXML public MenuItem rus;
    @FXML public MenuItem eng;
    @FXML public MenuItem exit;
    @FXML public Label labelSelectMember;
    @FXML public ComboBox comboBoxMembers;
    @FXML public TabPane TabPane;
    @FXML public Tab generalTab;
    @FXML public TextArea generalTextArea;
    @FXML public TextField textField;
    @FXML public Button send;


    private static byte lang = 0;
    private DialogWindows dialogWindows;

    public ButtonsController() {
        dialogWindows = new DialogWindows();
    }

    public void initButtons() {
        file.setText(Buttons.FILE.value(lang));
        authorization.setText(Buttons.AUTHORIZATION.value(lang));
        language.setText(Buttons.LANGUAGE.value(lang));
        eng.setText(Buttons.ENGLISH.value(lang));
        rus.setText(Buttons.RUSSIAN.value(lang));
        exit.setText(Buttons.EXIT.value(lang));
        labelSelectMember.setText(Buttons.SELECT_MEMBER.value(lang));
        generalTab.setText(Buttons.GENERAL.value(lang));
        send.setText(Buttons.SEND.value(lang));
    }

    public void onAuthorizationClick(ActionEvent actionEvent) {
        dialogWindows.loginWindow(false, lang);
    }

    public void onLangEngClick(ActionEvent actionEvent) {
        this.lang = 0;
        initButtons();
    }

    public void onLangRusClick(ActionEvent actionEvent) {
        this.lang = 1;
        initButtons();
    }

    public void onExitClick(ActionEvent actionEvent) {
        if (dialogWindows.exitWindow(lang)) {
            System.exit(0);
        }
    }

    public  byte getLang() {
        return lang;
    }
    public  void setLang(byte language) {
        lang = language;
    }

    public void onSendButtonClick(ActionEvent actionEvent) {
    }
}