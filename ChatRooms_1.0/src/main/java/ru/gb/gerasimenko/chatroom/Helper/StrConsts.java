package ru.gb.gerasimenko.chatroom.Helper;

public enum StrConsts {
    LOCALHOST("localhost"),
    WAITIN_NEXT_CONNECTION("Wainting next connection ..."),
    SERV_RUNNING("The server is running ..."),
    SERV_WAITNG("The server is waiting ..."),
    COMMA(","),
    END_LINE("\n"),
    SESSION_CLOSED("Session closed.");


    private String str;

    StrConsts(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
