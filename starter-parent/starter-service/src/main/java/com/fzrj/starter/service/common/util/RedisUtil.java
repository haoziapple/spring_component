package com.fzrj.starter.service.common.util;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @className:com.fzrj.starter.service.common.util.RedisUtil
 * @description:redis工具类，通过注入使用,方法待丰富
 * @version:v1.0.0
 * @date:2017年5月17日 下午4:41:33
 * @author:WangHao
 */
@Component
public class RedisUtil
{
	// 操作String类型key
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> valOpsStr;

	// 操作Object类型key
	@Autowired
	RedisTemplate<Object, Object> redisTemplate;
	@Resource(name = "redisTemplate")
	ValueOperations<Object, Object> valOps;

	public void setString(String key, String value)
	{
		valOpsStr.set(key, value);
	}

	public String getString(String key)
	{
		return valOpsStr.get(key);
	}

	public void setObj(String key, Object value)
	{
		valOps.set(key, value);
	}

	public Object getObj(String key)
	{
		return valOps.get(key);
	}

}
