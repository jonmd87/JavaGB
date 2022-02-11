package ru.gb.gerasimenko.chatroom.Helper;

public enum Commands {
    CMD_SEPARATOR("###"),
    ARG_SEPARATOR("@@@"),
    STR_SEPARATOR("&&&"),
    UPDATE_USERLIST("/update_userList"),
    USER_MOVEMENTS("/user_movements"),
    REGISTRATION("/registration"),
    DELETE_ACCOUNT("/delete_account"),
    AUTH_IN("/auth_in"),
    LOGOUT("/end"),
    SEND_TO("/sendTO"),
    ERROR("/error"),
    BROADCAST("/Broadcast");

    private String commands;

    Commands(String commands) {
        this.commands = commands;
    }

    public String getStr() {
        return commands;
    }
}
