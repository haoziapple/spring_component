package com.fzrj.starter.service.config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

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
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import com.fzrj.starter.service.common.util.RedisUtil;
import com.fzrj.starter.service.common.util.SpringContextUtil;
import com.fzrj.starter.service.config.mq.RedisMqMapping;
import com.google.gson.JsonSyntaxException;

@Configuration
@Component
public class RedisConfig
{
	private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);

	@Autowired
	private SpringContextUtil springContexUtil;

	@Autowired
	private RedisMqMapping redisMqMapping;

	/**
	 * 类名、方法名分隔符
	 */
	public static final String SPLIT = ".";

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

	/**
	 * @Description:设置redis消息监听
	 * @param connectionFactory
	 * @return
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月16日 上午11:11:52
	 */
	@Bean
	public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory)
	{
		final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		Map<String, String> map = redisMqMapping.getMap();
		for (Entry<String, String> entry : map.entrySet())
		{
			this.addListener(container, entry.getKey(), entry.getValue());
		}

		return container;
	}

	/**
	 * @Description:向redis监听容器中添加监听器，通过反射方法调用spring中的bean
	 * @param container
	 * @param channel
	 * @param serviceMethod
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月16日 下午1:57:53
	 */
	private void addListener(RedisMessageListenerContainer container, final String channel, String serviceMethod)
	{
		final String[] smArray = serviceMethod.split("\\" + SPLIT);
		if (smArray.length > 1)
		{
			container.addMessageListener(new MessageListener()
			{

				@Override
				public void onMessage(Message message, byte[] pattern)
				{
					log.info("Message received: " + message.toString());
					invokeSpringMethod(channel, smArray, message);
				}
			}, new ChannelTopic(channel));
		}
	}

	/**
	 * @Description:调用spring容器中已注册的bean方法，只支持无参或1个参数的方法
	 * @param channel
	 * @param smArray
	 * @param message
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月16日 下午2:36:35
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void invokeSpringMethod(final String channel, final String[] smArray, Message message)
	{
		Object springBean = springContexUtil.getContext().getBean(smArray[0]);
		Method[] mArray = springBean.getClass().getDeclaredMethods();
		boolean methodFound = false;
		for (Method m : mArray)
		{
			if (m.getName().equals(smArray[1]))
			{
				methodFound = true;
				// 获取参数类型表
				Class[] clzzArray = m.getParameterTypes();
				try
				{
					if (clzzArray.length == 0)
					{
						// 调用无参方法
						m.invoke(springBean);
					}
					else if (clzzArray.length == 1)
					{
						// 调用只有一个参数的方法
						Class clzz = clzzArray[0];
						m.invoke(springBean, RedisUtil.gson.fromJson(message.toString(), clzz));
					}
					else
					{
						log.error("RedisMq Exception: only allow 0 or 1 parameter, channel: {}, serviceMethod: {}",
								channel, Arrays.asList(smArray));
					}
					break;
				}
				catch (JsonSyntaxException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e)
				{
					log.error("invoke failed, channel: " + channel + ", serviceMethod: " + smArray[0] + SPLIT
							+ smArray[1], e);
				}
			}
		}
		if (!methodFound)
		{
			log.error("RedisMq Exception: cannot find method, channel: {}, serviceMethod: {}", channel,
					Arrays.asList(smArray));
		}
	}
}
