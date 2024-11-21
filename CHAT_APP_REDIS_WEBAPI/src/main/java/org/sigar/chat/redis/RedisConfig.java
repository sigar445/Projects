package org.sigar.chat.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
@ConditionalOnProperty(name = "chat.communication.channel", havingValue = "redis")
public class RedisConfig {
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory("localhost", 6379);
    }

    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
      //  container.setTaskExecutor(new SimpleAsyncTaskExecutor());
        if (!container.isRunning()) {
            container.start();
        }
       // container.addMessageListener(listener, new PatternTopic("*")); // Subscribe to all channels
        return container;
    }
}


//@Bean
//public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory,
//                                                    RedisMessageListener listener) {
//    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//    container.setConnectionFactory(connectionFactory);
//    // container.addMessageListener(listener, new PatternTopic("*")); // Subscribe to all channels
//    return container;
//}