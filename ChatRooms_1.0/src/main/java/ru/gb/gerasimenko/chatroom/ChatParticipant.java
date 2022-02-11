package ru.gb.gerasimenko.chatroom;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatParticipant implements Closeable {
    private Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;

    public ChatParticipant(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    public ChatParticipant(ServerSocket serverSocket) {
        try {
            this.socket = serverSocket.accept();
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    @Override
    public void close() throws IOException {
        if (this.inputStream != null) {this.inputStream.close();}
        if (this.outputStream != null) {this.outputStream.close();}
        if (this.socket != null) {this.socket.close();}
    }

    public String readMessage() throws IOException {
        if (this.connectionActive()) {
            return this.inputStream.readUTF();
        }
        return null;
    }

    public void sendMessage(String message) throws IOException {
        if (this.connectionActive()) {
                this.outputStream.writeUTF(message);
        }
    }

    public boolean connectionActive() {
        return !this.socket.isClosed();
    }
}
