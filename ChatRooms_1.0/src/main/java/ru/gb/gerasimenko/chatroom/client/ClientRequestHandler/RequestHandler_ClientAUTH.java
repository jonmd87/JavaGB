package ru.gb.gerasimenko.chatroom.client.ClientRequestHandler;
import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler_Client;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

public class RequestHandler_ClientAUTH implements RequestHandler_Client {
    @Override
    public void handler(String data, ChatClient client) {
//        System.out.println("AUTh |" + data + "|");
        client.setNick(data);
        client.getButtonsController().setAuth();
        client.getButtonsController().generalTextArea.clear();
    }
}
