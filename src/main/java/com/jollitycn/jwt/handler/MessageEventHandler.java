package com.jollitycn.jwt.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.jollitycn.jwt.cache.UserCache;
import com.jollitycn.jwt.emit.EmitEvents;
import com.jollitycn.jwt.model.UserInfo;
import com.jollitycn.jwt.util.SocketUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息事件处理器
 *
 * @author jasonhongcn
 * @version 1.0
 * @since 2018/1/19
 */
@Component
public class MessageEventHandler {
    @Resource(name = "userCache")
    private UserCache userCache;
    private final SocketIOServer server;

    private static final Logger logger = LoggerFactory.getLogger(MessageEventHandler.class);

    @Autowired
    public MessageEventHandler(SocketIOServer server) {
        this.server = server;
    }

    //添加connect事件，当客户端发起连接时调用
    @OnConnect
    public void onConnect(SocketIOClient client) {
        if (client != null) {
            String username = client.getHandshakeData().getSingleUrlParam("username");
            String password = client.getHandshakeData().getSingleUrlParam("password");
            String sessionId = client.getSessionId().toString();
            logger.info("连接成功, username=" + username + ", password=" + password + ", sessionId=" + sessionId);
            if (username != null) {
                userCache.put(client.getSessionId().toString(), username);
            } else {
                //  client.
            }
        } else {
            logger.error("客户端为空");
        }
    }

    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String sessionId = client.getSessionId().toString();
        //SocketUtil.broadcastWithoutClient(server, client, Emit.EVENT_OFFLINE, userCache.get(sessionId));
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userCache.get(sessionId));
        userInfo.setCount(userCache.size());
        client.disconnect();
        userCache.remove(sessionId);
        SocketUtil.broadcastWithoutClient(server, client, EmitEvents.USER_LEFT, userInfo);
        logger.info("客户端断开连接, sessionId=" + sessionId);
    }

    // 消息接收入口
    @OnEvent(value = EmitEvents.MESSAGE_NEW )
    public void onMessage(SocketIOClient client, AckRequest ackRequest, Object chat) {
                logger.info("接收到客户端消息 onMessage:" + chat);
        if (ackRequest.isAckRequested()) {
            // send ack response with data to client
            //client.sendEvent();
            //     ackRequest.
            ackRequest.sendAckData(chat);
        }

        SocketUtil.broadcastWithoutClient(server, client, EmitEvents.MESSAGE_NEW, chat);
    }

//    // 消息接收入口
//    @OnEvent(value = EmitEvents.GROUP_MSG_SEND  )
//    public void group_msg_send(SocketIOClient client, AckRequest ackRequest, Message chat) {
//        logger.info("接收到客户端消息 group_msg_send:" + chat);
//        if (ackRequest.isAckRequested()) {
//            // send ack response with data to client
//            //client.sendEvent();
//            //     ackRequest.
//            ackRequest.sendAckData(chat);
//        }
//        SocketUtil.broadcastWithoutClient(server, client, EmitEvents.GROUP_MSG_RECEIVED, chat);
//    }

    // 上线
    @OnEvent(value = EmitEvents.USER_JOINED)
    public void user_join(SocketIOClient client, AckRequest ackRequest, String name) {
        logger.info("接收到客户端消息 user_join:" + name);
        UserInfo userInfo  = new UserInfo();
        userInfo.setUserName(name);
        userInfo.setCount(userCache.size());
        if (ackRequest.isAckRequested()) {
            // send ack response with data to client
            ackRequest.sendAckData(userInfo);
            //client.sendEvent("user_join,);
        }

        SocketUtil.broadcastWithoutClient(server, client, EmitEvents.USER_JOINED, userInfo);
    }

    @OnEvent(value = EmitEvents.USER_LEFT)
    public void offline(SocketIOClient client, AckRequest ackRequest, String name) {
        logger.info("接收到客户端消息 offline:" + name);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(name);
        userInfo.setCount(userCache.size());
        if (ackRequest.isAckRequested()) {
            // send ack response with data to client
            ackRequest.sendAckData(userInfo);
            //client.sendEvent("user_join,);
        }

        SocketUtil.broadcastWithoutClient(server, client, EmitEvents.USER_LEFT, userInfo);
    }


//    // 消息接收入口
//    @OnEvent(value =EmitEvents.GROUP_MSG_RECEIVED)
//    public void group_msg_received(SocketIOClient client, AckRequest ackRequest, Message chat) {
//        logger.info("接收到客户端消息 group_msg_received:" + chat);
//        if (ackRequest.isAckRequested()) {
//            // send ack response with data to client
//            ackRequest.sendAckData(chat);
//        }
//    }

    @OnEvent(value = EmitEvents.TYPING)
    public void userTyping(SocketIOClient client) {
        String sessionId = client.getSessionId().toString();
        logger.info("接收到客户端"+sessionId+"消息 userTyping:");
        SocketUtil.broadcastWithoutClient(server, client, EmitEvents.TYPING,  userCache.get(sessionId));
    }

    @OnEvent(value = EmitEvents.TYPING_STOP)
    public void userStopTyping(SocketIOClient client) {
        String sessionId = client.getSessionId().toString();
        logger.info("接收到客户端" + sessionId + "消息 userStopTyping:");
        SocketUtil.broadcastWithoutClient(server, client, EmitEvents.TYPING_STOP, userCache.get(sessionId));
    }

    // 登录接口
    @OnEvent(value =EmitEvents.USER_ADD)
    public void onLogin(SocketIOClient client, AckRequest ackRequest, UserInfo message) {
        logger.info("接收到客户端登录消息:" + message);
        if (ackRequest.isAckRequested()) {
            // send ack response with data to client
            userCache.put(client.getSessionId().toString(), message.getUserName());
            message.setCount(userCache.size());
            ackRequest.sendAckData(message);
        }
    }
}
