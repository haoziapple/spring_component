package com.fzrj.starter.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		StringRedisSerializer serial = new StringRedisSerializer();
		template.setKeySerializer(serial);
		template.setValueSerializer(serial);
		template.setHashValueSerializer(serial);
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
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
				Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		// StringRedisSerializer默认使用UTF8编码
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

}
