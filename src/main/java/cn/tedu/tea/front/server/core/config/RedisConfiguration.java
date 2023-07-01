package cn.tedu.tea.front.server.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;

/**
 * Redis配置類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Configuration
public class RedisConfiguration {

    public RedisConfiguration() {
        log.info("創建配置類對象：RedisConfiguration");
    }

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) {//自動裝配的屬性
        //new obj
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        //Connection
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //key(String)
        redisTemplate.setKeySerializer(RedisSerializer.string());
        //value(Serializable)
        redisTemplate.setValueSerializer(RedisSerializer.json());
        return redisTemplate;
    }
}
