package com.haozi.spring.simple.service;

/**
 * @className:com.haozi.spring.simple.service.DemoServiceImpl
 * @description:服务实现方法
 * @version:v1.0.0
 * @date:2017年4月28日 下午5:04:00
 * @author:WangHao
 */
public class DemoServiceImpl implements DemoService
{

	@Override
	public String sayHello(String name)
	{
		return "Hello, " + name;
	}

}
