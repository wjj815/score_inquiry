package com.wangjj.scoreinquirywxback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName : RedisConfig
 * @Author : wangJJ
 * @Date : 2020/1/3 19:33
 * @Description : redis的配置
 */
@Configuration
public class RedisConfig {

	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){

		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		////参照StringRedisTemplate内部实现指定序列化器
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(keySerializer());
		redisTemplate.setHashKeySerializer(keySerializer());
		redisTemplate.setValueSerializer(valueSerializer());
		redisTemplate.setHashValueSerializer(valueSerializer());
		return redisTemplate;
	}

	private RedisSerializer<String> keySerializer(){
		return new StringRedisSerializer();
	}

	//使用Jackson序列化器
	private RedisSerializer<Object> valueSerializer(){
		return new GenericJackson2JsonRedisSerializer();
	}
}
