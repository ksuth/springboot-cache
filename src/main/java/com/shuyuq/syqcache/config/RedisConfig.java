package com.shuyuq.syqcache.config;


import com.shuyuq.syqcache.entity.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class RedisConfig {


    @Primary
    @Bean("redisTemplate")
    RedisTemplate getRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,Object> redisTemplate=new RedisTemplate<Object,Object>();
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
    @Primary
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory, ResourceLoader resourceLoader) {
        RedisCacheWriter redisCacheWriter=RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> objectSerializationPair = RedisSerializationContext.SerializationPair
                .fromSerializer(serializer);
        RedisCacheConfiguration redisCacheConfiguration=
                RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(objectSerializationPair);
        return new RedisCacheManager(redisCacheWriter,redisCacheConfiguration);
    }

    @Bean(value = "employeeCacheManager")
    public RedisCacheManager employeeCacheManager(RedisConnectionFactory redisConnectionFactory,
                                           ResourceLoader resourceLoader) {
        RedisCacheWriter redisCacheWriter=RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> redisSerializer = new Jackson2JsonRedisSerializer<>(Employee.class);
        RedisSerializationContext.SerializationPair<Employee> objectSerializationPair =
                RedisSerializationContext.SerializationPair
                .fromSerializer(redisSerializer);
        RedisCacheConfiguration redisCacheConfiguration=
                RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(objectSerializationPair);
        return new RedisCacheManager(redisCacheWriter,redisCacheConfiguration);
    }




}
