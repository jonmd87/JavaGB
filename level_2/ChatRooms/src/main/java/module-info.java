module ru.gb.gerasimenko.chatroom {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.gb.gerasimenko.chatroom to javafx.fxml;
    exports ru.gb.gerasimenko.chatroom;
    exports ru.gb.gerasimenko.chatroom.ChatServer;
    opens ru.gb.gerasimenko.chatroom.ChatServer to javafx.fxml;
}