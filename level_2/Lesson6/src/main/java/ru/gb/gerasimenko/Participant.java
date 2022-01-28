package ru.gb.gerasimenko;

import ru.gb.gerasimenko.Helper.Define;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Participant {
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public Participant(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            this.inputStream = createInputStream();
            this.outputStream = createOutputStream();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public Participant(ServerSocket serverSocket) {
        try {
            this.socket = serverSocket.accept();
            this.inputStream = createInputStream();
            this.outputStream = createOutputStream();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public String readMessage() throws IOException {
        if (this.connectionActive()) {
            return this.inputStream.readUTF();
        }
        else {
            this.close();
        }
        return null;
    }

    public void sendMessage(String message) throws IOException {
        if (outputStream != null) {
            this.outputStream.writeUTF(message);
        }
    }

    public boolean connectionActive() {
        return ((this.socket != null) && (this.inputStream != null) && (this.outputStream != null));
    }

    private DataOutputStream createOutputStream() throws IOException {
        return new DataOutputStream(this.socket.getOutputStream());
    }

    private DataInputStream createInputStream() throws IOException {
        return new DataInputStream(this.socket.getInputStream());
    }

    public void startCommandListener() {
        Thread listener = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    final String message = scanner.nextLine();
                    try { sendMessage(message);} catch (IOException e) {e.printStackTrace();}
                }
            }
        });
        listener.setDaemon(true);
        listener.start();
    }

    public void close() throws IOException {
        if (this.socket != null) {this.socket.close();}
        if (this.inputStream != null) {this.inputStream.close();}
        if (this.outputStream != null) {this.outputStream.close();}
    }
}
