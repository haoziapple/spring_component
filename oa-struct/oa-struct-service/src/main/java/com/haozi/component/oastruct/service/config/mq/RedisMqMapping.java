package com.haozi.component.oastruct.service.config.mq;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @className:com.haozi.component.oastruct.service.config.mq.RedisMqMapping
 * @description:RedisMq的map配置信息，决定了由哪个service处理channel上监听到的信息
 * @version:v1.0.0
 * @date:2017年8月16日 上午11:34:59
 * @author:WangHao
 */
@ConfigurationProperties(prefix = "redis.subscribe")
@PropertySource("classpath:pub-sub.properties")
@Component
public class RedisMqMapping
{
	private Map<String, String> map;

	public Map<String, String> getMap()
	{
		return map;
	}

	public void setMap(Map<String, String> map)
	{
		this.map = map;
	}
}
