package com.fzrj.starter.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig
{

	/**
	 * @Description:配置StringRedisTemplate，主要是序列化器的配置
	 * @param factory
	 * @return
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月15日 下午4:25:39
	 */
	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory)
	{
		StringRedisTemplate template = new StringRedisTemplate(factory);
		StringRedisSerializer serializer = new StringRedisSerializer();
		template.setKeySerializer(serializer);
		template.setValueSerializer(serializer);
		template.setHashValueSerializer(serializer);
		template.afterPropertiesSet();
		return template;
	}

	/**
	 * @Description:配置redisTemplate，用来操作object
	 * @param factory
	 * @return
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月15日 下午4:48:21
	 */
	@Bean
	public RedisTemplate<String, Object> objRedisTemplate(RedisConnectionFactory factory)
	{
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		// 定义的序列化方式
		GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();

		// StringRedisSerializer默认使用UTF8编码
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(serializer);
		template.setHashKeySerializer(serializer);
		template.setHashValueSerializer(serializer);
		template.afterPropertiesSet();
		return template;
	}
}
