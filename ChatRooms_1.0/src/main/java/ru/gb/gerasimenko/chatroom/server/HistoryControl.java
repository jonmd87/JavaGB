package ru.gb.gerasimenko.chatroom.server;


import ru.gb.gerasimenko.chatroom.Helper.DgtlConsts;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

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
    }

    public String getLastLines() {
        try {
            String mess = new String(this.input.readAllBytes(), StandardCharsets.UTF_8);
            return pullMessages(Arrays.asList(mess.split(StrConsts.END_LINE.getStr())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String pullMessages(List<String> list) {
        String lastMsg = "";
        int length = DgtlConsts.HUNDRED.value();
        if (list.size() > 0) {
            list = (list.size() > length) ? list.subList(list.size() - length, list.size()) : list;
            for (String s : list) {
                lastMsg += s + StrConsts.END_LINE.getStr();
            }

        }
        return lastMsg;
    }

    public void writeInHistoryFile(String data) {
        try {
            this.output.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initFileStreams() {
        try {
            this.input = new BufferedInputStream(new FileInputStream(file));
            this.output = new BufferedOutputStream(new FileOutputStream(file, true));
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
