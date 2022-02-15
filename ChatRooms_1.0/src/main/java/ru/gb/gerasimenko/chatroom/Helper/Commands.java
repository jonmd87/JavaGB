package ru.gb.gerasimenko.chatroom.Helper;

public enum Commands {
    CMD_SEPARATOR("###"),
    ARG_SEPARATOR("@@@"),
    STR_SEPARATOR("&&&"),
    TARGET_DELIVERY("/targ_delivery"),
    UPDATE_USERS_LIST("/update_userList"),
    USER_MOVEMENTS("/user_movements"),
    AUTH_IN("/auth_in"),
    LOGOUT("/end"),
    NOTIFICATION("/notification"),
    BROADCAST("/Broadcast"),
    DB_REGISTER("/DB_registration"), //CMD+nick+login+password,
    DB_UPDATE_NICK("/DB_update_login"),
    ;

    private String commands;

    Commands(String commands) {
        this.commands = commands;
    }

    public String getStr() {
        return commands;
    }
}
