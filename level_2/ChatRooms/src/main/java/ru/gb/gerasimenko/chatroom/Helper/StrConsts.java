package ru.gb.gerasimenko.chatroom.Helper;

public enum StrConstants {
    SEPARATOR("\t@\t"),
    TO_GENERAL("/general"),
    TO_("/"),
    SERVER("Server: "),
    CLIENT("Client: "),
    LOCALHOST("localhost"),
    AUTH_STARTED("Authorisation started ..."),
    AUTH_STOPPED("Authorisation stopped"),
    WAITIN_NEXT_CONNECTION("Wainting next connection ..."),
    SERV_RUNNING("The server is running ..."),
    SERV_WAITNG("The server is waiting ..."),
    SERVER_CONNNECTED("Server connected."),
    CLIENT_CONNNECTED("Client connected."),
    SESSION_CLOSED("Session closed."),
    QUERY("Query: "),
    ANSWER("Answer: ");


    private String str;

    StrConstants(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
