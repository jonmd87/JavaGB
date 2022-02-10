package ru.gb.gerasimenko.chatroom.Helper;

public enum Commands {
    NEW_MEMBER("&new_member&"),
    UPDATE_USERLIST("$update_userList$"),
    MEMBER_LEFT("&member_left&"),
    CMD_SEPARATOR("###"),
    STR_SEPARATOR("@&@"),
    REGISTRATION("/registration"),
    DELETE_ACCOUNT("/delete_account"),
    AUTH_IN("/auth_in"),
    LOGOUT("/end"),
    SEND_TO("/sendTO"),
    BROADCAST("/Broadcast");

    private String commands;

    Commands(String commands) {
        this.commands = commands;
    }

    public String getStr() {
        return commands;
    }
}
