package ru.gb.gerasimenko;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AppFileOutInputStreams {
    public static void main(String[] args) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("fileInOutPutStreams.txt", true)) {
            final byte[] bytes = "john".getBytes(StandardCharsets.UTF_8);
            fileOutputStream.write(bytes);
            fileOutputStream.write("\n".getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (final FileInputStream fileInputStream = new FileInputStream("fileInOutPutStreams.txt")) {
            int x;
            while ((x = fileInputStream.read()) != -1) {
                System.out.print((char)x);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
