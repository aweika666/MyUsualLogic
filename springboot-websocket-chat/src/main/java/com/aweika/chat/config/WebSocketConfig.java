package com.aweika.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author: Aweika_Fang
 * @date: 2020/8/29
 * @description:
 */
@Configuration
public class WebSocketConfig {
    /**
     * 用于扫描和注册所有携带ServerEndPoint注解的实例。
     * <p>
     * PS:若部署到外部容器 则无需提供此类。
     */

    /*
    这里需要特别提醒：ServerEndpointExporter 是由Spring官方提供的标准实现，
    用于扫描ServerEndpointConfig配置类和@ServerEndpoint注解实例。
    使用规则也很简单：
    1.如果使用默认的嵌入式容器 比如Tomcat 则必须手工在上下文提供ServerEndpointExporter。
    2. 如果使用外部容器部署war包，则不要提供提供ServerEndpointExporter，因为此时SpringBoot默认将扫描服务端的行为交给外部容器处理。

                作者：yizhiwazi
                链接：https://www.jianshu.com/p/55cfc9fcb69e
                来源：简书
                著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {

        return new ServerEndpointExporter();
    }
}
