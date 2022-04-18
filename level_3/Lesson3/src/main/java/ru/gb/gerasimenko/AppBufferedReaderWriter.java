package ru.gb.gerasimenko;

import java.io.*;

public class AppBufferedReaderWriter {
    public static void main(String[] args) {
        try (final BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("bufferedWriter.txt"))) {
            for (int i = 0; i < 10; i++) {
                bufferedWriter.write("john");
                bufferedWriter.write("\n");
                bufferedWriter.flush(); // принудительно записать в файл не дожидаясь заполнения буфера
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (final BufferedReader reader = new BufferedReader(new FileReader("bufferedWriter.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
