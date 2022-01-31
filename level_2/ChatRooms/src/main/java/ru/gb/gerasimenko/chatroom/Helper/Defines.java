package ru.gb.gerasimenko.chatroom.Helper;

public enum Defines {
    MAX_STRING_LENTH(5);


    private int val;

    Defines(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
