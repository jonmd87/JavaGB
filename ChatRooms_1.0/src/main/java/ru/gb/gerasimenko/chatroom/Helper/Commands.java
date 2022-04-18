package ru.gb.gerasimenko.chatroom.Helper;

public enum Commands {
    TECHNICAL_MSG("TECH_"),
    CMD_SEPARATOR("###"),
    ARG_SEPARATOR("@@@"),
    STR_SEPARATOR("&&&"),
    TARGET_DELIVERY("/targ_delivery"),
    UPDATE_USERS_LIST("/TECH_update_userList"),
    USER_MOVEMENTS("/TECH_user_movements"),
    AUTH_IN("/auth_in"),
    LOGOUT("/end"),
    EMPTY("/empty"),
    NOTIFICATION("/notification"),
    BROADCAST("/Broadcast"),
    DB_REGISTER("/DB_registration"), //CMD+nick+login+password,
    UPDATE_DATA("/UpDate_userData"),
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
