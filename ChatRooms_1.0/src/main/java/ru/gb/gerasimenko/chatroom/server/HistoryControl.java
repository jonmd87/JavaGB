package ru.gb.gerasimenko.chatroom.server;


import ru.gb.gerasimenko.chatroom.Helper.DgtlConsts;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class HistoryControl {
    private final String file;
    private Writer writer;

    public HistoryControl(String nick) {
        this.file = StrConsts.HISTORY_PATH.getStr() + File.separator + nick + StrConsts.TXT.getStr();
        createHistoryFile();
        initFileStreams();
    }

    public String getHistory() {
        final List<String> list = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                list.add(temp);
                if (list.size() > DgtlConsts.HUNDRED.value()) {
                    list.remove(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.join(StrConsts.END_LINE.getStr(), list);
    }

    public void writeInHistoryFile(String data) {
        try {
            this.writer.write(data + StrConsts.END_LINE.getStr());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initFileStreams() {
        try {
            this.writer = new BufferedWriter(new FileWriter(file, true));
        } catch (IOException e) {
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

    public void close() {
        try {
            this.writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
