package ru.gb.gerasimenko.chatroom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.gb.gerasimenko.chatroom.Helper.Buttons;
import ru.gb.gerasimenko.chatroom.Helper.Phrases;

import java.io.IOException;
import java.util.Optional;

public class ChatRoomApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatRoomApplication.class.getResource("BlaBlaRoom.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 640);
        stage.setTitle(Phrases.MAIN_TITLE.value((byte)1));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws Exception {
            super.stop();
    }
}