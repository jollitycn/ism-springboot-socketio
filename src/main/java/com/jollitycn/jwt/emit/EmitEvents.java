package com.jollitycn.jwt.emit;


import io.socket.client.Manager;
import io.socket.emitter.Emitter;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author jasonhongcn
 */
public class EmitEvents{

    private static final Logger logger = Logger.getLogger(EmitEvents.class.getName());
//    public static final String LOGIN ="login" ;
    public static final String USER_ADD = "user_add";
//    public static final String user_join ="user_join ;
    public static final String CONNECT = "connect";
    public static final String MESSAGE_NEW = "message";
//    public static final String GROUP_MSG_SEND = "group_msg_send";
//    public static final String GROUP_MSG_RECEIVED = "group_msg_received";
    public static final String USER_JOINED = "user_join";
    public static final String USER_LEFT = "user_left";
    public static final String TYPING = "typing";
    public static final String TYPING_STOP = "typing_stop";
//    public static final String EVENT_OFFLINE = "offline";

    /**
     * Called on a connection.
     */
    public static final String EVENT_CONNECT = "connect";

    public static final String EVENT_CONNECTING = "connecting";

    /**
     * Called on a disconnection.
     */
    public static final String EVENT_DISCONNECT = "disconnect";


//    public static final String EVENT_user_join= "user_join;
    /**
     * Called on a connection error.
     *
     * <p>Parameters:</p>
     * <ul>
     *   <li>(Exception) error data.</li>
     * </ul>
     */
    public static final String EVENT_ERROR = "error";


//    public static final String EVENT_SOMEONE_OFFLINE = "someone_offline";

    public static final String EVENT_CONNECT_ERROR = Manager.EVENT_CONNECT_ERROR;

    public static final String EVENT_CONNECT_TIMEOUT = Manager.EVENT_CONNECT_TIMEOUT;

    public static final String EVENT_RECONNECT = Manager.EVENT_RECONNECT;

    public static final String EVENT_RECONNECT_ERROR = Manager.EVENT_RECONNECT_ERROR;

    public static final String EVENT_RECONNECT_FAILED = Manager.EVENT_RECONNECT_FAILED;

    public static final String EVENT_RECONNECT_ATTEMPT = Manager.EVENT_RECONNECT_ATTEMPT;

    public static final String EVENT_RECONNECTING = Manager.EVENT_RECONNECTING;

    public static final String EVENT_PING = Manager.EVENT_PING;

    public static final String EVENT_PONG = Manager.EVENT_PONG;

//    protected static Map<String, Integer> events = new HashMap<String, Integer>() {{
//        put(EVENT_CONNECT, 1);
//        put(EVENT_CONNECT_ERROR, 1);
//        put(EVENT_CONNECT_TIMEOUT, 1);
//        put(EVENT_CONNECTING, 1);
//        put(EVENT_DISCONNECT, 1);
//        put(EVENT_ERROR, 1);
//        put(EVENT_RECONNECT, 1);
//        put(EVENT_RECONNECT_ATTEMPT, 1);
//        put(EVENT_RECONNECT_FAILED, 1);
//        put(EVENT_RECONNECT_ERROR, 1);
//        put(EVENT_RECONNECTING, 1);
//        put(EVENT_PING, 1);
//        put(EVENT_PONG, 1);
//        put(EVENT_user_join,1);
//    }};

}


