package com.aweika.chat.server;

import com.alibaba.fastjson.JSON;
import com.aweika.chat.entity.Message;
import org.apache.tomcat.websocket.WsSession;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Aweika_Fang
 * @date: 2020/8/29
 * @description:
 */
@Component
@ServerEndpoint("/chat")//标记此类为服务端
public class WebSocketChatServer {

    /**
     * 全部在线会话  PS: 基于场景考虑 这里使用线程安全的Map存储会话对象。
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();


    /**
     * 当客户端打开连接：1.添加会话对象 2.更新在线人数
     */
    @OnOpen
    public void onOpen(Session session) {
        onlineSessions.put(session.getId(), session);
        sendMessageToAll(Message.toJsonStr(Message.ENTER, "", "", onlineSessions.size()));
    }

    /**
     * 当客户端发送消息：1.获取它的用户名和消息 2.发送消息给所有人
     * <p>
     * PS: 这里约定传递的消息为JSON字符串 方便传递更多参数！
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        Message message = JSON.parseObject(jsonStr, Message.class);
        sendMessageToAll(Message.toJsonStr(Message.SPEAK, message.getUsername(), message.getMsg(), onlineSessions.size()));
    }

    /**
     * 当关闭连接：1.移除会话对象 2.更新在线人数
     */
    @OnClose
    public void onClose(Session session) {
        onlineSessions.remove(session.getId());
        sendMessageToAll(Message.toJsonStr(Message.QUIT, "", "下线了！", onlineSessions.size()));
    }

    /**
     * 当通信发生异常：打印错误日志
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 公共方法：发送信息给所有人
     */
    private static void sendMessageToAll(String jsonMsg) {
        onlineSessions.forEach((id, session) -> {
            try {
                session.getBasicRemote().sendText(jsonMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
