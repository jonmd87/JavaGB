package ru.gb.gerasimenko;

import java.io.*;

public class AppBufferedOutInPutStream {
    public static void main(String[] args) {
        final long start = System.currentTimeMillis();
        try (final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                                    new FileOutputStream("bufferedInOutPutStream.txt"))) {
            for (int i = 0; i < 1_000_000; i++) {
                bufferedOutputStream.write(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);

        try (final BufferedInputStream bufferedInputStream = new BufferedInputStream(
                                    new FileInputStream("bufferedInOutPutStream.txt"))) {
                int x;
                while ((x = bufferedInputStream.read()) != -1) {
                    System.out.print(x);
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
