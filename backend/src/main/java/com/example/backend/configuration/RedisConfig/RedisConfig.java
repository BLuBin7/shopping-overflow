package com.example.backend.configuration.RedisConfig;

import com.example.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public JedisConnectionFactory getConnectionFactoty(){
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        return connectionFactory;
    }

    @Bean
    public RedisTemplate<String, User> redisTemplate() {
        var redisTemplate = new RedisTemplate<String,User>();
        redisTemplate.setConnectionFactory(getConnectionFactoty());
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }


    @Bean
    public HashOperations<String, String, User> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

//    // just an example, I'm not using it in this project
//    @Autowired
//    RedisConnectionFactory factory;
//    @Bean
//    public RedisTemplate<String, User> redisTemplateTokenUser() {
//        var redisTemplate = new RedisTemplate<String,User>();
//        redisTemplate.setConnectionFactory(factory);
//        return redisTemplate;
//    }
}
