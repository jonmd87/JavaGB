package ru.gb.gerasimenko.chatroom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;

import java.io.IOException;

public class ChatRoomApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatRoomApplication.class.getResource("BlaBlaRoom.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 720);
        stage.setTitle(Phrases.MAIN_TITLE.value((byte)1));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}