package ru.gb.gerasimenko;

import java.io.*;

public class AppPrintStream {
    public static void main(String[] args) {
        try (final FileOutputStream fileOutputStream = new FileOutputStream("printStream.txt");
             final PrintStream printStream = new PrintStream(fileOutputStream)) {
            printStream.println("Hello world!");
            printStream.println(1000);
            printStream.println(1.0001);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
