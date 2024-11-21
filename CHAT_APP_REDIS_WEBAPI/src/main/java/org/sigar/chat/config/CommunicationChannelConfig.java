//package org.sigar.chat.config;
//
//import org.sigar.chat.channel.CommunicationChannel;
//import org.sigar.chat.http.HttpCommunicationChannel;
//import org.sigar.chat.redis.RedisCommunicationChannel;
//import org.sigar.chat.websocket.WebSocketCommunicationChannel;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class CommunicationChannelConfig {
//
//    @Bean
//    @ConditionalOnProperty(name = "chat.communication.channel", havingValue = "redis")
//    public CommunicationChannel redisChannel(RedisCommunicationChannel redisCommunicationChannel) {
//        return redisCommunicationChannel;
//    }
//
//    @Bean
//    @ConditionalOnProperty(name = "chat.communication.channel", havingValue = "websocket")
//    public CommunicationChannel websocketChannel(WebSocketCommunicationChannel websocketCommunicationChannel) {
//        return websocketCommunicationChannel;
//    }
//    @Bean
//    @ConditionalOnProperty(name = "chat.communication.channel", havingValue = "http")
//    public CommunicationChannel httpChannel(HttpCommunicationChannel httpCommunicationChannel) {
//        return httpCommunicationChannel;
//    }
//}
//
