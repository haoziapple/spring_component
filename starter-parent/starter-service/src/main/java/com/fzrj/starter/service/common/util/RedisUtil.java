package com.fzrj.starter.service.common.util;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @className:com.fzrj.starter.service.common.util.RedisUtil
 * @description:redis工具类,通过注入使用,方法待丰富,对象序列化使用Gson
 * @version:v1.0.0
 * @date:2017年5月17日 下午4:41:33
 * @author:WangHao
 */
@Component
public class RedisUtil
{
	private final Gson gson = new GsonBuilder().create();

	// 操作String类型
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> stringOps;
	// 操作Object类型
	@Autowired
	RedisTemplate<String, Object> objRedisTemplate;
	@Resource(name = "objRedisTemplate")
	ValueOperations<String, Object> objOps;

	public void set(String key, String value)
	{
		stringOps.set(key, value);
	}

	public String get(String key)
	{
		return stringOps.get(key);
	}

	public void set(String key, Object value)
	{
		objOps.set(key, value);
	}

	public Object getObj(String key)
	{
		return objOps.get(key);
	}

	public void incr(String key, Long delta)
	{
		stringOps.increment(key, delta);
	}

	public void del(String key)
	{
		stringRedisTemplate.delete(key);
	}
}
