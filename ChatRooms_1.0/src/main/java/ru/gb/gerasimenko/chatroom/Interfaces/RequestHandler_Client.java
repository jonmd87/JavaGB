package ru.gb.gerasimenko.chatroom.Interfaces;
import ru.gb.gerasimenko.chatroom.client.ChatClient;

public interface RequestHandler_Client {
    public String handler(String data, ChatClient client);
    default public String[] dataToStringArray(String data, String separator) {
        String[] split = data.split(separator);
        for (String s : split) {
            System.out.println("[" + s + "]");
        }
        return split;
    }
}
