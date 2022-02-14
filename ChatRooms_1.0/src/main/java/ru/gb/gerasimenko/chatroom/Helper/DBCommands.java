package ru.gb.gerasimenko.chatroom.Helper;

public enum DBCommands {
    CREATE("/create"),
    DELETE("/delete"),
    UPDATE("/update"),
    SELECTALL("/selectALL"),
    SELECT_BY_NICK("/selectByNick");

    private String cmd;

    DBCommands(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }
}
