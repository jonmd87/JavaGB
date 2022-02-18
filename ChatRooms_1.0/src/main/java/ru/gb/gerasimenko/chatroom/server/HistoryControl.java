package ru.gb.gerasimenko.chatroom.server;


import ru.gb.gerasimenko.chatroom.Helper.DgtlConsts;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class HistoryControl {
    private String nick;
    private String file;
    private BufferedInputStream input;
    private BufferedOutputStream output;

    public HistoryControl(String nick) {
        this.nick = nick;
        this.file = StrConsts.HISTORY_PATH.getStr() + File.separator + nick + StrConsts.TXT.getStr();
        createHistoryFile();
        initFileStreams();
        getLastLines();
    }

    public static void main(String[] args) {

    }





    private void getLastLines() {
        int value = DgtlConsts.HUNDRED.value();
        StringBuilder lastLines = new StringBuilder();
    }

    private static void writeInHistoryFile() {
    }

    public void readFromHistoryFile() {

    }

    private void initFileStreams() {
        try {
            this.input = new BufferedInputStream(new FileInputStream(file));
            this.output = new BufferedOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createHistoryFile() {
        if (!Files.exists(Path.of(this.file))) {
            try {
                Files.createFile((Path.of(this.file)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
