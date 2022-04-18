package ru.gb.gerasimenko;

import java.io.*;

public class AppDataInOutPutStream {
    public static void main(String[] args) {
        try (final DataOutputStream dataOutputStream = new DataOutputStream(
                                new FileOutputStream("dataInOutPutStream.txt"))) {
            dataOutputStream.writeInt(100);
            dataOutputStream.writeUTF("hello");
            dataOutputStream.writeBoolean(true);
            dataOutputStream.writeDouble(1.01);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (final DataInputStream dataInputStream = new DataInputStream(
                                new FileInputStream("dataInOutPutStream.txt"))) {
            System.out.println(dataInputStream.readUTF());
            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readBoolean());
            System.out.println(dataInputStream.readDouble());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
