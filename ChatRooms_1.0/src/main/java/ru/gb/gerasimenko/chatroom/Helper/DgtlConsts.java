package ru.gb.gerasimenko.chatroom.Helper;

public  enum DgtlConsts {
    PORT(8080),
    PADDING(10),
    MIN_NICK_LENTH(3),
    MIN_LOGPASS_LEN(3),
    SEC_120(20);  // in release change to 120 seconds


    private int digit;

    DgtlConsts(int digit) {
        this.digit = digit;
    }

    public int value() {
        return this.digit;
    }
}
