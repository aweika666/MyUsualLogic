package com.aweika.chat.entity;

/**
 * @author: Aweika_Fang
 * @date: 2020/8/29
 * @description:
 */
/**
 * WebSocket 聊天消息类
 */

import com.alibaba.fastjson.JSON;

/**
 * WebSocket 聊天消息类
 */
public class Message {

    public static final String ENTER = "ENTER";
    public static final String SPEAK = "SPEAK";
    public static final String QUIT = "QUIT";

    private String type;//消息类型

    private String username; //发送人

    private String msg; //发送消息

    private Integer onlineCount; //在线用户数

    public static String toJsonStr(String type, String username, String msg, int onlineTotal) {
        return JSON.toJSONString(new Message(type, username, msg, onlineTotal));
    }

    public Message(String type, String username, String msg, int onlineCount) {
        this.type = type;
        this.username = username;
        this.msg = msg;
        this.onlineCount = onlineCount;
    }

    //这里省略get/set方法 请自行补充


    public String getType() {
        return type;
    }

    public Message setType(String type) {
        this.type = type;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Message setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Message setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Integer getOnlineCount() {
        return onlineCount;
    }

    public Message setOnlineCount(Integer onlineCount) {
        this.onlineCount = onlineCount;
        return this;
    }

    public Message() {

    }
}

