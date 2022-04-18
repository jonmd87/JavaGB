package ru.gb.gerasimenko;

import java.io.File;
import java.io.IOException;

// Simple File

public class AppFile {
    public static void main(String[] args) {
        File file = new File("./myDir/myfile.txt");
        File dir = new File("myDir");
        if (!dir.exists()) {
            dir.mkdir();
            if (!(file.exists())) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        new AppFile().dirWalker(new File("/home/evgen/prog/javaProjects/gbJava/level_3/Lesson3"));
    }

    public void dirWalker(File file) {
        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()) {
                System.out.println("[D]--|" + listFile.getName() + "|");
                dirWalker(listFile);
            } else {
                System.out.println("\t\t|__<" + listFile.getName() + ">");
            }
        }
    }
}
