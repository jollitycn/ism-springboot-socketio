package com.jollitycn.jwt.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Message
 *
 * @author jasonhongcn
 * @version 1.0
 * @since 2018/1/30
 */

public class Message implements Serializable {

    public static final int TYPE_MESSAGE = 0;
    public static final int TYPE_LOG = 1;
    public static final int TYPE_ACTION = 2;
    private String event;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;

    private Message() {
    }

    public static Message createMessage(int typeMessage) {
        Message message =  new Message();
        message.type = typeMessage;
        return message;
    }

    public Message username(String username) {
        this.from = username;
        return this;
    }

    public Message message(String message) {
        this.content = message;
        return this;
    }

    public Message build() {
        return this;
    }

    @Override
    public String toString() {
        return "Message{" +
                "event='" + event + '\'' +
                ", type='" + type + '\'' +
                ", channel='" + channel + '\'' +
                ", room='" + room + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", from='" + from + '\'' +
                ", loc='" + loc + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                '}';
    }

    private String channel;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }


    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    private String room;

    private String avatarUrl;
    private String from;
    private String loc;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    private String content;

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    private int userCount;

}
