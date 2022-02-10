package ru.gb.gerasimenko.chatroom.Handlers;

import ru.gb.gerasimenko.chatroom.Helper.Commands;
import ru.gb.gerasimenko.chatroom.Interfaces.RequestHandler;
import java.util.HashMap;
import java.util.Map;

public class Handler {
    private  final Map<String, RequestHandler> handler;

    public Handler() {
        this.handler = createHandler();
    }

    private Map<String, RequestHandler> createHandler() {
        Map<String, RequestHandler> handler = new HashMap<>();
        handler.put(Commands.REGISTRATION.getStr(), new RegistrationHandler());
        handler.put(Commands.DELETE_ACCOUNT.getStr(), new DeleteAccountHandler());
        handler.put(Commands.AUTH_IN.getStr(), new AuthenticationHandler());
        handler.put(Commands.LOGOUT.getStr(), new LogoutHandler());
        handler.put(Commands.SEND_TO.getStr(), new TargetedDeliveryHandler());
        handler.put(Commands.BROADCAST.getStr(), new BroadcastHandler());
        return handler;
    }

    public Map<String, RequestHandler> getHandler() {
        return handler;
    }
}
