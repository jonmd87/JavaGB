package ru.gb.gerasimenko;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryControl {
    private String nick;
    private String chatHistory = "chatHistory_";
    private String newLine = "\n";
    private String fileExtension = ".txt";
    private String filePath;
    private String dir = "./History/";
    private BufferedWriter bufWriter;

    public HistoryControl(String nick) {
        this.nick = nick;
        createFilePath();
        createHistoryFile(new File(this.dir), new File(this.filePath));
        openFileStreams();
    }

    private void createFilePath() {
        this.filePath = this.dir + this.chatHistory + this.nick + this.fileExtension;
    }

    private void createHistoryFile(File dir, File path) {
        if (!dir.exists()) {
            dir.mkdir();
        }
        if (!path.exists()) {
            try {
                path.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openFileStreams() {
        try {
            this.bufWriter = new BufferedWriter(new FileWriter(this.filePath, true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readLastLines(int val) {
        List<String> lines = new ArrayList<>();
        if (val > 0) {
            String line;
            try (final BufferedReader bufReader = new BufferedReader(new FileReader(this.filePath))) {
                while ((line = bufReader.readLine()) != null) {
                    lines.add(line);
                    if (lines.size() > val) {
                        lines.remove(0);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return String.join("\n", lines);
    }

    public void log (String str) {
        try {
            this.bufWriter.write(str + this.newLine);
            this.bufWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renameHistoryFile(String newNick) {
        File oldFile = new File(this.filePath);
        this.nick = newNick;
        createFilePath();
        File newFile = new File(this.filePath);
        if (oldFile.exists() && !newFile.exists()) {
            if (oldFile.renameTo(newFile)) {
                System.out.println("renamed");
            }
        }
    }
}
