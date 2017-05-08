package com.haozi.spring.simple.dubbotest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.haozi.spring.simple.service.DemoService;

/**
 * @className:com.haozi.spring.simple.dubbotest.Consumer
 * @description:dubbo服务消费者
 * @version:v1.0.0
 * @date:2017年5月2日 下午2:38:06
 * @author:WangHao
 */
public class Consumer
{
	public static void main(String[] args) throws Exception
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:consumer.xml" });
		DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
		String hello = demoService.sayHello("haozi"); // 执行远程方法

		System.out.println(hello); // 显示调用结果

	}
}
