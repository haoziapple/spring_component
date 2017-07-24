package com.haozi.demo.springboot.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haozi.demo.springboot.bean.BookBean;

/**
 * @className:com.haozi.demo.springboot.controller.RedisController
 * @description:TODO
 * @version:v1.0.0
 * @date:2017年3月7日 下午7:26:25
 * @author:WangHao
 */

@RestController
@RequestMapping("/redis")
@Configuration
public class RedisController
{
	private final static Logger logger = LoggerFactory.getLogger(RedisController.class);

	@Bean
	public ChannelTopic topic()
	{
		return new ChannelTopic("pubsub:queue");
	}

	@Bean
	public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory, ChannelTopic topic)
	{
		final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(new MessageListener()
		{

			@Override
			public void onMessage(Message message, byte[] pattern)
			{
				// TODO Auto-generated method stub
				System.out.println("Message received: " + message.toString());
			}
		}, topic);

		return container;
	}

	@Autowired
	ChannelTopic topic;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> valOpsStr;

	@Autowired
	RedisTemplate<Object, Object> redisTemplate;

	@Resource(name = "redisTemplate")
	ValueOperations<Object, Object> valOps;

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public String get(String key)
	{
		logger.debug("redis get 操作， key:" + key);
		String s = valOpsStr.get(key);
		return s;
	}

	@RequestMapping(value = "/set", method = RequestMethod.POST)
	public String set(String key, String value)
	{
		logger.debug("redis set 操作， key:" + key + " value:" + value);
		valOpsStr.set(key, value);
		return "success";
	}

	@RequestMapping(value = "/setBean", method = RequestMethod.POST)
	public String setBean(String name, String author, String price)
	{
		BookBean bean = new BookBean();
		bean.setName(name);
		bean.setAuthor(author);
		bean.setPrice(price);
		logger.debug("redis setBean 操作:" + bean);
		valOps.set(name, bean);
		return "success";
	}

	@RequestMapping(value = "/getBean", method = RequestMethod.POST)
	public BookBean getBean(String name)
	{
		logger.debug("redis getBean 操作， name:" + name);
		BookBean bean = (BookBean) valOps.get(name);
		return bean;
	}

	@RequestMapping(value = "/setJsonBean", method = RequestMethod.POST)
	public String setJsonBean(@RequestBody BookBean bookBean)
	{
		logger.debug("redis setJsonBean 操作:" + bookBean);
		valOps.set(bookBean.getName(), bookBean);
		return "success";
	}

	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	public String publish()
	{
		BookBean bean = new BookBean();
		bean.setName("name");
		bean.setAuthor("author");
		bean.setPrice("price");
		stringRedisTemplate.convertAndSend(topic.getTopic(), bean.toString());
		return "success";
	}
}
