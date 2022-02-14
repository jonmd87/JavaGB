module ru.gb.gerasimenko.chatroom {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ru.gb.gerasimenko.chatroom to javafx.fxml;
    exports ru.gb.gerasimenko.chatroom;
    exports ru.gb.gerasimenko.chatroom.Interfaces;
    opens ru.gb.gerasimenko.chatroom.Interfaces to javafx.fxml;
    exports ru.gb.gerasimenko.chatroom.server;
    opens ru.gb.gerasimenko.chatroom.server to javafx.fxml;
    exports ru.gb.gerasimenko.chatroom.server.ServerRequestHandlers;
    opens ru.gb.gerasimenko.chatroom.server.ServerRequestHandlers to javafx.fxml;
}