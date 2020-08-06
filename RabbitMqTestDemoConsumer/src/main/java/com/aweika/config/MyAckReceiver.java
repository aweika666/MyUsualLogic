package com.aweika.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Michael
 * @date: 2020/7/31
 * @description: 消息接收处理类
 */
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            System.out.println("接收到消息队列推送的消息");

            //因为传递消息的时候用的map传递,所以将Map从Message内取出需要做些处理
            String msg = message.toString();
            //可以点进Message里面看源码,单引号直接的数据就是我们的map消息数据
            String[] msgArray = msg.split("'");

            Map msgMap = mapStringToMap(msgArray[1].trim());

            Object messageId=msgMap.get("messageId");
            Object messageData=msgMap.get("messageData");
            Object createTime=msgMap.get("createTime");
            System.out.println("  MyAckReceiver  messageId:"+messageId+"  messageData:"+messageData+"  createTime:"+createTime);
            System.out.println("消费的主题消息来自："+message.getMessageProperties().getConsumerQueue());
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            //channel.basicReject(deliveryTag, true);//为true会重新放回队列
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }

    //{key=value,key=value,key=value} 格式转换成map
    private Map<String, String> mapStringToMap(String str) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",");
        Map<String, String> map = new HashMap<String, String>();

        for (int i = 0; i < strs.length; i++) {
            String valueString = strs[i];
            if (valueString.contains("=")){
                String key = valueString.split("=")[0].trim();
                String value = valueString.split("=")[1];
                //防止传输信息里 有 逗号,
                i++;
                if (i < strs.length && ! strs[i].contains("=")){
                    //不包含则执行 拼接
                    value = value + "," + strs[i];
                }else {
                    //包含
                    i--;
                }
                map.put(key, value);
            }
        }

        /*for (String string : strs) {
            String key = string.split("=")[0].trim();
            String value = string.split("=")[1];
            map.put(key, value);
        }*/
        return map;
    }

}
