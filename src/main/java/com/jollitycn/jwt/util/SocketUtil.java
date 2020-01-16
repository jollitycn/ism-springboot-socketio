package com.jollitycn.jwt.util;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;

import java.util.function.Consumer;

public class SocketUtil {
    public static void broadcastWithoutClient(SocketIOServer server, SocketIOClient client, String eventName, Object obj) {
        Consumer<? super SocketIOClient> consumer = new Consumer<SocketIOClient>() {
            @Override
            public void accept(SocketIOClient t) {
                if (t != client) {
//                    Message msg = new Message();
//                    msg.setMessageType("user_join);
//                    msg.setFrom(name);
                    t.sendEvent(eventName, obj);
                }
            }
        };
        server.getAllClients().forEach(consumer);
    }
}
