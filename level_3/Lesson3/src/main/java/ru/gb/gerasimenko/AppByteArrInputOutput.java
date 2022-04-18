package ru.gb.gerasimenko;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


//        InputStream inputStream;   // work with bytes
//        OutputStream outputStream; // work with bytes
//        Writer writer;  // work with symbols
//        Reader reader;  // work with symbols
public class AppByteArrInputOutput {
    public static void main(String[] args) {
            byte[] arr = new byte[10];

        try (final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            for (int i = 0; i < 10; i++) {
                byteArrayOutputStream.write((byte) (65 + i));
            }
            arr = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arr)) {
                int x;
                while ((x = byteArrayInputStream.read()) != -1) {
                    System.out.println(x);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
