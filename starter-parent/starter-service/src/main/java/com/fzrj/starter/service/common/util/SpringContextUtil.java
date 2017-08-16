package com.fzrj.starter.service.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @className:com.fzrj.starter.service.common.util.SpringContextUtil
 * @description:spring上下文工具类，用来获取spring context
 * @version:v1.0.0
 * @date:2017年8月16日 下午1:13:27
 * @author:WangHao
 */
@Component
public class SpringContextUtil implements ApplicationContextAware
{
	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		context = applicationContext;
	}

	public ApplicationContext getContext()
	{
		return context;
	}
}
