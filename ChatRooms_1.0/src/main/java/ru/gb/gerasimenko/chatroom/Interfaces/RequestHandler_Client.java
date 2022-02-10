package ru.gb.gerasimenko.chatroom.Interfaces;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

import java.util.ArrayList;

public interface RequestHandler_Client {
    public String handler(String data, ChatClient client);
    default public ArrayList<String> dataToArrayList(String data, String separator) {
        ArrayList<String> list = new ArrayList<>();
        String[] split = data.split(separator);
        for (String s : split) {
            System.out.println("[" + s + "]");
            list.add(s);
        }
        return list;
    }
}
