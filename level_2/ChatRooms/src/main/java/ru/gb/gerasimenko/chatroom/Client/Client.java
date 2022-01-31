package ru.gb.gerasimenko.chatroom.Client;

import ru.gb.gerasimenko.chatroom.ButtonsController;
import ru.gb.gerasimenko.chatroom.Helper.Defines;
import ru.gb.gerasimenko.chatroom.Participant;

public class Client {
    private Participant client;
    private String nick;
    private ButtonsController buttonsController;
    private DialogWindows windows;

    public static void main(String[] args) {
       Client client = new Client();
       client.start();
    }

    public Client() {
        this.buttonsController = new ButtonsController();
      //  this.client = new Participant(Defines.LOCALHOST.getStr(), Defines.PORT.getVal());
        this.windows = new DialogWindows();
    }

    public void start() {
        buttonsController.setLang(windows.chooseLanguage());
    }
}
