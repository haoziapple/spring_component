package com.haozi.spring.simple.dubbotest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @className:com.haozi.spring.simple.dubbotest.Provider
 * @description:dubbo服务提供者
 * @version:v1.0.0
 * @date:2017年5月2日 下午2:36:57
 * @author:WangHao
 */
public class Provider
{
	public static void main(String[] args) throws Exception
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:provider.xml" });
		context.start();

		System.in.read();

	}
}
