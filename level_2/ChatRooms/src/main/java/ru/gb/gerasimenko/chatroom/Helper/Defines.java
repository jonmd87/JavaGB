package ru.gb.gerasimenko.chatroom.Helper;

public enum Defines {
    SERVER("Server: ", 0),
    CLIENT("Client: ", 0),
    LOCALHOST("localhost", 0),
    PORT("8080", 8080),
    SERV_RUNNING("The server is running ...", 0),
    SERV_WAITNG("The server is waiting ...", 0),
    SERVER_CONNNECTED("Server connected.", 0),
    CLIENT_CONNNECTED("Client connected.", 0),
    SESSION_CLOSED("Session closed.", 0),
    QUERY("Query: ", 0),
    ANSWER("Answer: ", 0),
    END_SESION("/end", 0),
    MAX_STRING_LENTH("", 5);


    private int val;
    private String str;

    Defines(String str, int val) {
        this.str = str;
        this.val = val;
    }

    public int getVal() {
        return val;
    }
    public String getStr() {
        return str;
    }
}
