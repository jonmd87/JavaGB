package ru.gb.gerasimenko.chatroom.server;


import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Helper.StrConsts;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryControl {
    private String nick;
    private String filePath;
    private BufferedWriter bufferedWriter;


    public HistoryControl(String nick) {
        this.nick = nick;
        createPathToHistoryFile();
        createHistoryDirAndFile();
        initBufferedWriter();
    }

    private void createPathToHistoryFile() {
        this.filePath = StrConsts.HISTORY_DIR.getStr() + StrConsts.PREFIX.getStr();
        this.filePath += this.nick + StrConsts.SUFFIX.getStr();
    }

    private void createHistoryDirAndFile() {
        File dir = new File(StrConsts.HISTORY_DIR.getStr());
        File historyFile = new File(this.filePath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        if (!historyFile.exists()) {
            try {
                historyFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initBufferedWriter() {
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(this.filePath, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLastLines(int val) {
        List<String> lines = new ArrayList<>();
        String line = null;
        try (final BufferedReader buffReader = new BufferedReader(new FileReader(this.filePath))) {
            while ((line = buffReader.readLine()) != null) {
                lines.add(line);
                if (lines.size() > val) {
                    lines.remove(0);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines.size() > 0 ? String.join("\n", lines) : Commands.EMPTY.getStr();
    }

    public void writeInHistoryFile(String line) {
        if (line != null) {
            try {
                this.bufferedWriter.write(line);
                this.bufferedWriter.write(StrConsts.END_LINE.getStr());
                this.bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void deleteHistoryFile() {
        File file = new File(this.filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public void close() {
        if (this.bufferedWriter != null) {
            try {
                this.bufferedWriter.close();
                this.bufferedWriter = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean renameHistoryFile(String newNick) {
        System.out.println(filePath);
        File oldFile = new File(this.filePath);
        this.nick = newNick;
        createPathToHistoryFile();
        System.out.println(filePath);
        File newFile = new File(this.filePath);
        if (oldFile.exists() && !newFile.exists()) {
            if (oldFile.renameTo(newFile)) {
                this.close();
                this.initBufferedWriter();
                System.out.println("changed");
                return true;
            }
        }
        return false;
    }
}
