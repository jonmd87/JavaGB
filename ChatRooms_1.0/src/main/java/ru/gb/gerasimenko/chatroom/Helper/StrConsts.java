package ru.gb.gerasimenko.chatroom.Helper;

public enum StrConsts {
    LOCALHOST("localhost"),
    WAITING_NEXT_CONNECTION("Wainting next connection ..."),
    SERV_RUNNING("The server is running ..."),
    SERV_WAITING("The server is waiting ..."),
    COMMA(","),
    END_LINE("\n"),
    HISTORY_DIR("./History/"),
    PREFIX("chatHistory_"),
    CHATUSERS("chatUsers"),
    SUFFIX(".txt"),
    SESSION_CLOSED("Session closed.");


    private String str;

    StrConsts(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
