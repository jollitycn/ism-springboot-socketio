package com.jollitycn.jwt.cache;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/7/21 0021.
 * 定义Cache用于保存所有和Web端的连接：
 */
@Service("userCache")
public class UserCache {
    //String：EventType类型
    private Map<String,String> users=new ConcurrentHashMap<String,String>();

    //用户发送消息添加
//    public void addClient(SocketIOClient client, Message msgBean){
//        clients.put(msgBean.getFrom(),client);
//    }

    //用户连接进来添加用户
    public void put(String key,String value){
        users.put(key,value);
    }

    //用户退出时移除
//    public void remove(Message msgBean) {
//        clients.remove(msgBean.getFrom());
//    }

    //获取所有
    public  String get(String key) {
        return users.get(key);
    }

    public void remove(String key) {
         users.remove(key);
    }
    public int size(){
        return users.size();
    }
}