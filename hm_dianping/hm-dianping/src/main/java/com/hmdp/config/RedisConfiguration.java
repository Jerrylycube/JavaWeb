package com.hmdp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {
	
	@Bean
	public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		log.info("初始化RedisTemplate");
		RedisTemplate redisTemplate = new RedisTemplate<>();
		//设置RedisTemplate的连接工厂
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		//设置RedisTemplate的Key序列化方式
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		//设置RedisTemplate的Value序列化方式
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new StringRedisSerializer());
//		// Value 使用 JSON 序列化 (需要引入 jackson 依赖)
//		GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();
//		redisTemplate.setValueSerializer(jsonSerializer); // <--- 改为 JSON 序列化
		return redisTemplate;
	}
}